package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.repository.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("ImageRepository")
public class JdbcImageRepository implements ImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Image image) {
        String sql = "insert into image (volume, type, width, height, created_at) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql,
            image.volume().volume(),
            image.type().name(),
            image.specification().width(),
            image.specification().height(),
            image.createdAt());
    }

    @Override
    public Image findById(Long id) {
        String sql = "select id, volume, type, width, height, created_at, updated_at from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getDouble(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
