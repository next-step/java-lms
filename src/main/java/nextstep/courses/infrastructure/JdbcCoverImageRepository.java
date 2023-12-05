package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.repository.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("courseRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage image) {
        String sql = "insert into cover_image (url, volume, format, width, height, created_at) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.url(), image.volume(), image.format(), image.aspectRatio().width(), image.aspectRatio().height(), image.createdAt());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, url, volume, format, width, height, created_at, updated_at from cover_image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getString(4),
                rs.getInt(5),
                rs.getInt(6),
                toLocalDateTime(rs.getTimestamp(7)),
                toLocalDateTime(rs.getTimestamp(8)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
