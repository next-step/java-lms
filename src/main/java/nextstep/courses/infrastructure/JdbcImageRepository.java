package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.image.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final CoverImage image) {
        final ImagePixel imagePixel = image.imagePixel();
        final ImageType imageType = image.imageType();

        String sql = "insert into image (size, width, height, type, created_at) values(?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.size(), imagePixel.width(), imagePixel.height(), imageType.name(), image.createdAt());
    }

    @Override
    public CoverImage findById(final Long id) {
        String sql = "select id, `size`, width, height, type, created_at, updated_at from image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(2),
                new ImagePixel(rs.getInt(3), rs.getInt(4)),
                ImageType.valueOf(rs.getString(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
