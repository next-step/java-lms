package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.ImageRepository;
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
        String sql = "insert into image (title, image_size, image_type, width, height) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.getTitle(), image.getImageSize(), image.getImageType().toString(), image.getWidth(), image.getHeight());
    }

    @Override
    public Image findById(Long id) {
        String sql = "select id, title, image_size, image_type, width, height from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getInt(6));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
