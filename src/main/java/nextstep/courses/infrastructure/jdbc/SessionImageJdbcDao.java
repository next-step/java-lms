package nextstep.courses.infrastructure.jdbc;

import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

@Component
public class SessionImageJdbcDao {
    private final JdbcOperations jdbcTemplate;

    public SessionImageJdbcDao(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(SessionCoverImageEntity entity) {
        String sql = "insert into session_cover_image (session_id, width, height, extension, capacity) values(?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            entity.getSessionId(),
            entity.getWidth(),
            entity.getHeight(),
            entity.getExtension(),
            entity.getCapacity()
        );
    }

    public SessionCoverImageEntity findById(Long id) {
        String sql = "select id, session_id, width, height, extension, capacity from session_cover_image where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper(), id);
    }

    public SessionCoverImageEntity findBySessionId(Long sessionId) {
        String sql = "select id, session_id, width, height, extension, capacity from session_cover_image where session_id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper(), sessionId);
    }

    private RowMapper<SessionCoverImageEntity> rowMapper() {
        return (rs, rowNum) -> new SessionCoverImageEntity(
            rs.getLong("id"),
            rs.getLong("session_id"),
            rs.getInt("width"),
            rs.getInt("height"),
            rs.getString("extension"),
            rs.getLong("capacity")
        );
    }
}