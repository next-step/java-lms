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
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image.Builder()
                .id(rs.getLong(1))
                .type(ImageType.valueOf(rs.getString(2)))
                .size(rs.getDouble(3))
                .dimension(rs.getDouble(4), rs.getDouble(5))
                .build();
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
