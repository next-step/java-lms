package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.image.Image;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcTemplate jdbcTemplate;

    public JdbcImageRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Image image) {
        String sql = "INSERT INTO image (capacity, image_type, width, height) VALUES (?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.getCapacity(), image.getImageType(), image.getWidth(), image.getHeight());
    }
}
