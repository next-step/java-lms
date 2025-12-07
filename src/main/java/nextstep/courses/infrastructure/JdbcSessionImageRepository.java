package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.SessionCoverImage;
import nextstep.courses.domain.image.SessionImageRepository;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import nextstep.courses.infrastructure.mapper.SessionCoverImageMapper;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionCoverImage image) {
        SessionCoverImageEntity entity = SessionCoverImageMapper.toEntity(image);

        String sql = "insert into session_cover_image (session_id, width, height, extension, capacity) values(?, ?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
            entity.getSessionId(),
            entity.getWidth(),
            entity.getHeight(),
            entity.getExtension(),
            entity.getCapacity()
        );
    }

    @Override
    public SessionCoverImage findById(Long id) {
        String sql = "select id, session_id, width, height, extension, capacity from session_cover_image where id = ?";

        SessionCoverImageEntity entity = jdbcTemplate.queryForObject(sql, rowMapper(), id);
        return SessionCoverImageMapper.toDomain(entity);
    }

    @Override
    public SessionCoverImage findBySessionId(Long sessionId) {
        String sql = "select id, session_id, width, height, extension, capacity from session_cover_image where session_id = ?";

        SessionCoverImageEntity entity = jdbcTemplate.queryForObject(sql, rowMapper(), sessionId);
        return SessionCoverImageMapper.toDomain(entity);
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
