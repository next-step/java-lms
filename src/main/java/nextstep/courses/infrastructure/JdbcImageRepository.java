package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.*;
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
        String sql = "insert into image (name, volume, height, width, session_id) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.name(), image.volume(), image.height(), image.width(), image.sessionId());
    }

    @Override
    public Image findById(Long id) {
        String sql = "select id, name, volume, height, width, session_id from image where  id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> {
            Long imageId = rs.getLong(1);
            String name = rs.getString(2);
            ImageName imageName = new ImageName(name);
            int volume = rs.getInt(3);
            ImageSize imageSize = new ImageSize(volume);
            int height = rs.getInt(4);
            int width = rs.getInt(5);
            ImagePixel imagePixel = new ImagePixel(width, height);
            Long sessionId = rs.getLong(6);
            return new Image(imageId, imageName, imageSize, imagePixel, sessionId);
        };
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
