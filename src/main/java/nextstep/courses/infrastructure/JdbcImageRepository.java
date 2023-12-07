package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.ImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Long save(Image image) {
        KeyHolder keyHolder = new GeneratedKeyHolder();
        String sql = "insert into image (title, image_size, image_type, width, height) values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(connection -> {
            PreparedStatement ps = connection.prepareStatement(sql, new String[]{"id"});
            ps.setString(1, image.getTitle());
            ps.setLong(2, image.getImageSize());
            ps.setString(3, image.getImageType().toString());
            ps.setInt(4, image.getWidth());
            ps.setInt(5, image.getHeight());
            return ps;
        }, keyHolder);
        return (Long) keyHolder.getKey();
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
