package nextstep.image.infrastructure;

import nextstep.image.domain.Image;
import nextstep.image.domain.ImageRepository;
import nextstep.image.domain.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Image> findById(Long id) {
        String sql = "select " +
                "id, " +
                "width, " +
                "height, " +
                "image_type, " +
                "size from image where id = ?";

        return Optional.ofNullable(jdbcTemplate.queryForObject(
                sql,
                (rs, rowNum) -> new Image(
                        rs.getLong(1),
                        rs.getInt(2),
                        rs.getInt(3),
                        ImageType.valueOf(rs.getString(4)),
                        rs.getLong(5)
                ),
                id));
    }
}
