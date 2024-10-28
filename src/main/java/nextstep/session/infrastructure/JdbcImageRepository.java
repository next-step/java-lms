package nextstep.session.infrastructure;

import nextstep.session.domain.image.Image;
import nextstep.session.domain.image.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Image image) {
        String sql = "insert into image (name, width, height, capacity, created_at, updated_at) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
                image.getName(),
                image.getSize().getWidth().getWidth(),
                image.getSize().getHeight().getHeight(),
                image.getCapacity().getCapacity(),
                image.getDateDomain().getCreatedAt(),
                image.getDateDomain().getUpdatedAt()
        );
    }

    @Override
    public Image findById(Long id) {
        String sql = "select id, name, width, height, capacity, created_at, updated_at from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getInt(5),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<Image> findBySessionId(Long sessionId) {
        String sql = "select id, name, width, height, capacity, created_at, updated_at from image where session_id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getInt(4),
                rs.getInt(5),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
