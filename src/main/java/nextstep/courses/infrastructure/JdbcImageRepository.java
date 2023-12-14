package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.image.Image;
import nextstep.courses.domain.course.session.image.ImageRepository;
import nextstep.courses.domain.course.session.image.ImageType;
import nextstep.courses.domain.course.session.image.Images;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.support.GeneratedKeyHolder;
import org.springframework.jdbc.support.KeyHolder;
import org.springframework.stereotype.Repository;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.Statement;
import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Objects;
import java.util.Optional;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcOperations jdbcTemplate;
    KeyHolder keyHolder = new GeneratedKeyHolder();

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Optional<Image> findById(Long id) {
        String sql = "select " +
                "id, image_size, image_type, image_width, image_height, session_id, creator_id, created_at, updated_at " +
                "from image where id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getInt(2),
                ImageType.find(rs.getString(3)),
                rs.getInt(4),
                rs.getInt(5),
                rs.getLong(7),
                rs.getTimestamp(8).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(9)));
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    @Override
    public Image save(Long sessionId, Image image) {
        ImageType imageType = image.getImageType();

        String sql = "insert into image " +
                "(image_size, image_type, image_width, image_height, session_id, creator_id, created_at, updated_at) " +
                "values(?, ?, ?, ?, ?, ?, ?, ?)";

        jdbcTemplate.update((Connection connection) -> {
            PreparedStatement ps = connection.prepareStatement(sql, Statement.RETURN_GENERATED_KEYS);
            ps.setInt(1, image.getImageSize());
            ps.setString(2, imageType.name());
            ps.setInt(3, image.getImageWidth());
            ps.setInt(4, image.getImageHeight());
            ps.setLong(5, sessionId);
            ps.setLong(6, image.getCreatorId());
            ps.setTimestamp(7, Timestamp.valueOf(image.getCreatedAt()));
            ps.setTimestamp(8, toTimeStamp(image.getCreatedAt()));
            return ps;
        }, keyHolder);

        Long imageId = Objects.requireNonNull(keyHolder.getKey()).longValue();
        image.setId(imageId);

        return image;
    }

    @Override
    public Images findAllBySessionId(Long sessionId) {
        String sql = "select " +
                "id, image_size, image_type, image_width, image_height, session_id, creator_id, created_at, updated_at " +
                "from image where session_id = ?";
        RowMapper<Image> rowMapper = (rs, rowNum) -> new Image(
                rs.getLong(1),
                rs.getInt(2),
                ImageType.find(rs.getString(3)),
                rs.getInt(4),
                rs.getInt(5),
                rs.getLong(7),
                rs.getTimestamp(8).toLocalDateTime(),
                toLocalDateTime(rs.getTimestamp(9)));

        List<Image> images = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new Images(images);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    private Timestamp toTimeStamp(LocalDateTime localDateTime) {
        if (localDateTime == null) {
            return null;
        }
        return Timestamp.valueOf(localDateTime);
    }
}
