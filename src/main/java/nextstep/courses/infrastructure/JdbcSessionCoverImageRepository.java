package nextstep.courses.infrastructure;

import nextstep.courses.infrastructure.engine.SessionCoverImageRepository;
import nextstep.courses.infrastructure.entity.SessionCoverImageEntity;
import nextstep.courses.infrastructure.util.LocalDateTimeConverter;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionCoverImageRepository")
public class JdbcSessionCoverImageRepository implements SessionCoverImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionCoverImageEntity entity) {
        String sql = "insert into session_cover_image (session_id, size, width, height, extension, created_at, updated_at) " +
                "values (?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, entity.getSessionId(),
                entity.getSize(),
                entity.getWidth(),
                entity.getHeight(),
                entity.getExtension(),
                entity.getCreatedAt(),
                entity.getUpdatedAt());
    }

    @Override
    public SessionCoverImageEntity findById(Long id) {
        String sql = "select id, session_id, size, width, height, extension, created_at, updated_at " +
                "from session_cover_image " +
                "where id = ?";
        return jdbcTemplate.queryForObject(sql, coverImageRowMapper(), id);
    }

    private RowMapper<SessionCoverImageEntity> coverImageRowMapper() {
        return (rs, rowNum) -> new SessionCoverImageEntity(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getInt(4),
                rs.getInt(5),
                rs.getString(6),
                LocalDateTimeConverter.convert(rs.getTimestamp(7)),
                LocalDateTimeConverter.convert(rs.getTimestamp(8))
        );

    }
}
