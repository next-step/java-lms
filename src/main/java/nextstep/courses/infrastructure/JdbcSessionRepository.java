package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.CoverImageRepository;
import nextstep.courses.domain.session.*;
import nextstep.qna.exception.NotFoundException;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Optional;
import java.util.Set;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    private final CoverImageRepository coverImageRepository;
    private final EnrollmentRepository enrollmentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, CoverImageRepository coverImageRepository, EnrollmentRepository enrollmentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.coverImageRepository = coverImageRepository;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public int save(Session session) {
        saveCoverImage(session);
        return saveSession(session);
    }

    private void saveCoverImage(Session session) {
        coverImageRepository.save(session.getCoverImage(), session.getId());
    }

    private int saveSession(Session session) {
        String sql = "INSERT INTO session (session_id, course_id, title, status, start_date, end_date, fee, max_enrollments) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, session.getId(), session.getCourseId(), session.getTitle(), session.getSessionStatus(),
                session.getStartDate(), session.getEndDate(), session.getFee(), session.getMaxEnrollments());
    }


    @Override
    public Optional<Session> findById(Long id) {
        String sql = "SELECT session_id, course_id, title, status, start_date, end_date, fee, max_enrollments " +
                "FROM session WHERE session_id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapSession, id));
    }

    private Session mapSession(ResultSet rs, int rowNum) throws SQLException {
        long sessionId = rs.getLong("session_id");
        long courseId = rs.getLong("course_id");
        String title = rs.getString("title");
        SessionStatus status = SessionStatus.valueOf(rs.getString("status"));
        LocalDateTime startDate = rs.getTimestamp("start_date").toLocalDateTime();
        LocalDateTime endDate = rs.getTimestamp("end_date").toLocalDateTime();
        long fee = rs.getLong("fee");
        int maxEnrollments = rs.getInt("max_enrollments");

        SessionPeriod period = SessionPeriod.of(startDate, endDate);
        CoverImage coverImage = getCoverImageBySessionId(sessionId);
        SessionBody sessionBody = SessionBody.of(courseId, title, period, coverImage);
        SessionEnrollment sessionEnrollment = getSessionEnrollmentBySessionId(status, sessionId);

        if (isPaidSession(fee, maxEnrollments)) {
            return new PaidSession(sessionId, sessionBody, sessionEnrollment, fee, maxEnrollments);
        }
        return new FreeSession(sessionId, sessionBody, sessionEnrollment);
    }

    private CoverImage getCoverImageBySessionId(long sessionId) {
        return coverImageRepository.findBySessionId(sessionId)
                .orElseThrow(NotFoundException::new);
    }

    private SessionEnrollment getSessionEnrollmentBySessionId(SessionStatus status, long sessionId) {
        Set<NsUser> enrolledUsers = enrollmentRepository.findEnrolledUsersBySessionId(sessionId);
        return SessionEnrollment.of(status, enrolledUsers);
    }

    private boolean isPaidSession(long fee, int maxEnrollments) {
        return fee != 0 && maxEnrollments != 0;
    }


}