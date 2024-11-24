package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.CoverImageRepository;
import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;
import java.util.Set;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    private final EnrollmentRepository enrollmentRepository;
    private final CoverImageRepository coverImageRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, EnrollmentRepository enrollmentRepository, CoverImageRepository coverImageRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.enrollmentRepository = enrollmentRepository;
        this.coverImageRepository = coverImageRepository;
    }

    @Override
    public int save(Session session) {
        return saveSession(session);
    }

    private int saveSession(Session session) {
        String sql = "INSERT INTO session (session_id, course_id, title, progress_status, recruitment_status, start_date, end_date, fee, " +
                "max_enrollments) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, session.getId(), session.getCourseId(), session.getTitle(), session.getProgressStatus(),
                session.getRecruitmentStatus(), session.getStartDate(), session.getEndDate(), session.getFee(), session.getMaxEnrollments()
        );
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "SELECT session_id, course_id, title, progress_status, recruitment_status, start_date, end_date, fee, max_enrollments " +
                "FROM session WHERE session_id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, this::mapSession, id));
    }

    private Session mapSession(ResultSet rs, int rowNum) throws SQLException {
        long sessionId = rs.getLong(1);
        long courseId = rs.getLong(2);
        String title = rs.getString(3);
        ProgressStatus progressStatus = ProgressStatus.valueOf(rs.getString(4));
        RecruitmentStatus recruitmentStatus = RecruitmentStatus.valueOf(rs.getString(5));
        LocalDateTime startDate = rs.getTimestamp(6).toLocalDateTime();
        LocalDateTime endDate = rs.getTimestamp(7).toLocalDateTime();
        long fee = rs.getLong(8);
        int maxEnrollments = rs.getInt(9);

        List<CoverImage> coverImages = coverImageRepository.findBySessionId(sessionId);

        SessionPeriod period = SessionPeriod.of(startDate, endDate);
        SessionBody sessionBody = SessionBody.of(title, period, coverImages);
        SessionEnrollment sessionEnrollment = getSessionEnrollmentBySessionId(progressStatus, recruitmentStatus, sessionId);

        return SessionFactory.create(sessionId, courseId, sessionBody, sessionEnrollment, fee, maxEnrollments);
    }

    private SessionEnrollment getSessionEnrollmentBySessionId(ProgressStatus progressStatus, RecruitmentStatus recruitmentStatus, long sessionId) {
        Set<Student> enrolledStudents = enrollmentRepository.findEnrolledStudentsBySessionId(sessionId);
        return SessionEnrollment.of(progressStatus, recruitmentStatus, EnrolledStudents.of(enrolledStudents));
    }

}