package nextstep.courses.infrastructure.session;

import nextstep.courses.domain.session.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("sessionRepository")
public class JdbcSessionRepository implements SessionRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into sessions (course_id, start_at, end_at, created_at, updated_at, cover_image_path, cover_image_name, payment_type, session_status, max_user_size) values (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, session.getCourseId(), session.getStartAt(), session.getEndAt(), session.getCreatedAt(), session.getUpdatedAt(), session.getCoverImagePath(), session.getCoverImageName(), session.getPaymentType(), session.getSessionStatus(), session.getMaxUserSize());
    }

    @Override
    public Session findById(Long id) {
        String sql = "select id, course_id, start_at, end_at, created_at, updated_at, cover_image_path, cover_image_name, payment_type, session_status, max_user_size from sessions where id = ?";
        RowMapper<Session> rowMapper = (rs, rowNum) -> new Session(
                rs.getLong("id"),
                rs.getLong("course_id"),
                new SessionPeriod(
                        toLocalDateTime(rs.getTimestamp("start_at")),
                        toLocalDateTime(rs.getTimestamp("end_at"))
                ),
                toLocalDateTime(rs.getTimestamp("created_at")),
                toLocalDateTime(rs.getTimestamp("updated_at")),
                new CoverImage(
                        rs.getString("cover_image_path"),
                        rs.getString("cover_image_name")
                ),
                PaymentType.valueOf(rs.getString("payment_type")),
                new SessionEnrollment(
                        SessionStatus.valueOf(rs.getString("session_status")),
                        rs.getInt("max_user_size")
                )
        );
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
