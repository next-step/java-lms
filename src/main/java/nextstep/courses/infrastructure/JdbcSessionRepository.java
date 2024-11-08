package nextstep.courses.infrastructure;

import nextstep.courses.domain.cover.CoverImage;
import nextstep.courses.domain.cover.ImageDimension;
import nextstep.courses.domain.cover.ImageSize;
import nextstep.courses.domain.session.*;
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

    private final EnrollmentRepository enrollmentRepository;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate, EnrollmentRepository enrollmentRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.enrollmentRepository = enrollmentRepository;
    }

    @Override
    public int save(Session session) {
        return saveSession(session);
    }

    private int saveSession(Session session) {
        String sql = "INSERT INTO session (session_id, course_id, title, status, start_date, end_date, fee, " +
                "max_enrollments, file_name, image_size, extension, width, height) " +
                "VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql, session.getId(), session.getCourseId(), session.getTitle(), session.getSessionStatus(),
                session.getStartDate(), session.getEndDate(), session.getFee(), session.getMaxEnrollments(), session.getFileName(),
                session.getImageSize(), session.getImageExtension(), session.getWidth(), session.getHeight()
        );
    }

    @Override
    public Optional<Session> findById(Long id) {
        String sql = "SELECT session_id, course_id, title, status, start_date, end_date, fee, max_enrollments, file_name, image_size, extension, width, height " +
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
        String fileName = rs.getString("file_name");
        ImageSize imageSize = ImageSize.of(rs.getInt("image_size"));
        String extension = rs.getString("extension");
        int width = rs.getInt("width");
        int height = rs.getInt("height");

        SessionPeriod period = SessionPeriod.of(startDate, endDate);
        CoverImage coverImage = CoverImage.of(fileName, imageSize, extension, ImageDimension.of(width, height));
        SessionBody sessionBody = SessionBody.of(title, period, coverImage);
        SessionEnrollment sessionEnrollment = getSessionEnrollmentBySessionId(status, sessionId);

        return SessionFactory.create(sessionId, courseId, sessionBody, sessionEnrollment, fee, maxEnrollments);
    }

    private SessionEnrollment getSessionEnrollmentBySessionId(SessionStatus status, long sessionId) {
        Set<NsUser> enrolledUsers = enrollmentRepository.findEnrolledUsersBySessionId(sessionId);
        return SessionEnrollment.of(status, enrolledUsers);
    }

}