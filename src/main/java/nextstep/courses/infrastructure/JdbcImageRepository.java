package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.image.Image;
import nextstep.courses.domain.course.image.ImageRepository;
import nextstep.courses.domain.course.image.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Optional;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Image> findById(Long id) {
        String sql = "select id, image_size, image_type, image_width, image_height, creator_id, created_at, updated_at from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getInt(2),
                ImageType.find(rs.getString(3)),
                rs.getInt(4),
                rs.getInt(5),
                rs.getLong(6),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public int save(Image image) {
        ImageType imageType = image.getImageType();

        String sql = "insert into image (image_size, image_type, image_width, image_height, creator_id, created_at, updated_at) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.getImageSize(), imageType.name(), image.getImageWidth(), image.getImageHeight(), image.getCreatorId(), image.getCreatedAt(), image.getUpdatedAt());
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
