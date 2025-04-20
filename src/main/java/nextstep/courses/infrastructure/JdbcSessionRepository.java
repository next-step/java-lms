package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Session findById(Long sessionId) {
        String sql = "SELECT id, created_at, updated_at, cover_image_size, cover_image_type, cover_image_width, cover_image_height, session_type, session_status, price, capacity FROM session WHERE id = ?";
        return jdbcTemplate.queryForObject(sql, sessionRowMapper(), sessionId);
    }

    private RowMapper<Session> sessionRowMapper() {
        return (rs, rowNum) -> {
            CoverImage coverImage = new CoverImage(
                    rs.getInt("cover_image_size"),
                    ImageType.valueOf(rs.getString("cover_image_type")),
                    rs.getInt("cover_image_width"),
                    rs.getInt("cover_image_height")
            );
            return new Session(
                    rs.getLong("id"),
                    toLocalDateTime(rs.getTimestamp("created_at")),
                    toLocalDateTime(rs.getTimestamp("updated_at")),
                    coverImage,
                    SessionType.valueOf(rs.getString("session_type")),
                    SessionStatus.valueOf(rs.getString("session_status")),
                    rs.getLong("price"),
                    rs.getObject("capacity", Long.class)
            );
        };
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

}
