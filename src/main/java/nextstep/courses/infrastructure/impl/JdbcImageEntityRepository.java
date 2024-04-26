package nextstep.courses.infrastructure.impl;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;
import nextstep.courses.entity.ImageEntity;
import nextstep.courses.infrastructure.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("imageRepository")
public class JdbcImageEntityRepository implements ImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcImageEntityRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(ImageEntity imageEntity) {
        String sql = "insert into image (size, type, width, height, session_id, created_at) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            imageEntity.getSize(),
            imageEntity.getType(),
            imageEntity.getWidth(),
            imageEntity.getHeight(),
            imageEntity.getSessionId(),
            imageEntity.getCreatedAt());
    }

    @Override
    public Optional<ImageEntity> findById(Long id) {
        String sql = "select id, size, type, width, height, session_id, created_at, updated_at from image where id = ?";
        RowMapper<ImageEntity> rowMapper = (rs, rowNum) -> new ImageEntity(
            rs.getLong(1),
            rs.getInt(2),
            rs.getString(3),
            rs.getInt(4),
            rs.getInt(5),
            rs.getLong(6),
            toLocalDateTime(rs.getTimestamp(7)),
            toLocalDateTime(rs.getTimestamp(8)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
