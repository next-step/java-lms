package nextstep.courses.infrastructure;

import nextstep.courses.domain.CoverImage;
import nextstep.courses.repository.CoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("coverImageRepository")
public class JdbcCoverImageRepository implements CoverImageRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(CoverImage image) {
        String sql = "insert into cover_image (id, size, extension, width, height, created_at) values(?, ?, ?, ?, ?, ?)";
        return jdbcTemplate.update(sql, image.getId(), image.getSize(), image.getExtension(), image.getWidth(), image.getHeight(), image.getCreatedAt());
    }

    @Override
    public CoverImage findById(Long id) {
        String sql = "select id, size, extension, width, height, created_at, updated_at from cover_image where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper(), id);
    }

    @Override
    public List<CoverImage> findAllBySessionId(Long id) {
        String sql = "select id, size, extension, width, height, created_at, updated_at from cover_image where session_id = ?";
        return jdbcTemplate.query(sql, rowMapper(), id);
    }

    private RowMapper<CoverImage> rowMapper() {
        RowMapper<CoverImage> rowMapper = (rs, rowNum) -> new CoverImage(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getInt(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));
        return rowMapper;
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
