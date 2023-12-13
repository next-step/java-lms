package nextstep.courses.infrastructure;

import nextstep.courses.domain.Image.CoverImage;
import nextstep.courses.repository.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("courseRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage image, Long sessionId) {
        String sql = "insert into cover_image (session_id, url, volume, format, width, height, created_at) values(?, ?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, sessionId, image.url(), image.volume(), image.format(), image.aspectRatio().width(), image.aspectRatio().height(), image.createdAt());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, session_id, url, volume, format, width, height, created_at, updated_at from cover_image where id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getString(3),
                rs.getLong(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getInt(7),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }

    @Override
    public List<CoverImage> findBySessionId(Long sessionId) {
        String sql = "select id, session_id, url, volume, format, width, height, created_at, updated_at from cover_image where session_id = ?";
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getString(3),
                rs.getLong(4),
                rs.getString(5),
                rs.getInt(6),
                rs.getInt(7),
                toLocalDateTime(rs.getTimestamp(8)),
                toLocalDateTime(rs.getTimestamp(9)));
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
