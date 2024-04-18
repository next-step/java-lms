package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.image.Capacity;
import nextstep.sessions.domain.image.Image;
import nextstep.sessions.domain.image.ImageSize;
import nextstep.sessions.domain.image.ImageType;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.Optional;

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

    @Override
    public Optional<Image> findById(long imageId) {
        String sql = "SELECT id, capacity, image_type, width, height FROM image WHERE id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long id = rs.getLong("id");
            int capacity = rs.getInt("capacity");
            String imageType = rs.getString("image_type");
            double width = rs.getDouble("width");
            double height = rs.getDouble("height");
            return new Image(id, new Capacity(capacity), ImageType.valueOf(imageType), new ImageSize(width, height));
        }, imageId));
    }
}
