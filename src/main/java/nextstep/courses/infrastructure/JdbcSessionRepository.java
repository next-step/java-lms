package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import org.springframework.jdbc.core.JdbcTemplate;

import java.sql.Timestamp;
import java.time.LocalDateTime;

public abstract class JdbcSessionRepository {
    protected final JdbcTemplate jdbcTemplate;

    protected JdbcSessionRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    protected void saveSession(Session session) {
        String sql = "insert into session (id, creator_id, course_id, start_at, end_at, cover_image_file_size, cover_image_type, cover_image_width, cover_image_height, status, created_at) values(?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql, session.toParameters());
    }

    protected static LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
