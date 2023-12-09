package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.CoverImage;
import nextstep.courses.domain.image.ImagePixel;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.image.ImageType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Optional;

@Repository("imageRepository")
public class JdbcImageRepository implements ImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final Long sessionId, final CoverImage image) {
        final ImagePixel imagePixel = image.imagePixel();
        final ImageType imageType = image.imageType();

        String sql = "insert into image (session_id, size, width, height, type, created_at) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, image.size(), imagePixel.width(), imagePixel.height(),
                imageType.name(), image.createdAt());
    }

    @Override
    public Optional<CoverImage> findById(final Long id) {
        String sql = "select id, session_id, `size`, width, height, type, created_at, updated_at from image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(3),
                new ImagePixel(rs.getInt(4), rs.getInt(5)),
                ImageType.valueOf(rs.getString(6)),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));
        return  Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, id));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }

    @Override
    public List<CoverImage> findAllBySessionId(final Long sessionId) {

        String sql = "select id, session_id, `size`, width, height, type, created_at, updated_at from image where session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(3),
                new ImagePixel(rs.getInt(4), rs.getInt(5)),
                ImageType.valueOf(rs.getString(6)),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));
        return  jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
