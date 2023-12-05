package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.repository.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

public class JdbcImageRepository implements ImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Image image) {
        String sql = "insert into image (path, width, height, image_type) values(? , ? , ? , ?)";
        return jdbcTemplate.update(sql, image.path(), image.width(), image.height() , image.imageType());
    }

    @Override
    public Image findById(Long id) {
        String sql = "select id, path, width, height, image_type from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getString(2),
                new Size(rs.getInt(3), rs.getInt(4)),
                ImageType.valueOf(rs.getString(5)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
