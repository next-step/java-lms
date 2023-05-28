package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image;
import nextstep.courses.domain.ImageRepository;
import nextstep.courses.domain.enums.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.net.URI;
import java.net.URISyntaxException;
import java.time.LocalDateTime;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Image findById(Long id) {
        String imageSql = "SELECT * FROM image WHERE id = ?";
        return jdbcTemplate.queryForObject(imageSql, imageRowMapper(), id);
    }

    @Override
    public int save(Image image) {
        String sql = "INSERT INTO image (name, uri, size, image_type, created_at) VALUES (?, ?, ?, ?,?)";
        return jdbcTemplate.update(sql, image.getName(), image.getUri().toString(), image.getSize(), image.getType().getValue(), LocalDateTime.now());
    }

    private RowMapper<Image> imageRowMapper() {
        return (rs, rowNum) -> {
            try {
                return new Image(
                        rs.getLong("id"),
                        rs.getString("name"),
                        new URI(rs.getString("uri")),
                        rs.getLong("size"),
                        ImageType.of(rs.getString("image_type"))
                );
            } catch (URISyntaxException e) {
                e.printStackTrace();
            }
            return null;
        };
    }
}
