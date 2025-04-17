package nextstep.images.infrastructure;

import nextstep.images.domain.Image;
import nextstep.images.domain.ImageRepository;
import nextstep.images.domain.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Image image) {
        String sql = "insert into image (id, type, size, width, height) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.getId(), image.getType().name(), image.getSize().getValue(),
                image.getDimension().getWidth(), image.getDimension().getHeight());
    }

    @Override
    public Image findById(Long id) {
        String sql = "select id, type, size, width, height from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                ImageType.valueOf(rs.getString(2)),
                rs.getFloat(3),
                rs.getFloat(4),
                rs.getFloat(5));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
