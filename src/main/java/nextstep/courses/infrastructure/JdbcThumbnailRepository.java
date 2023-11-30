package nextstep.courses.infrastructure;

import nextstep.courses.domain.Thumbnail;
import nextstep.courses.domain.ThumbnailRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("thumbnailRepository")
public class JdbcThumbnailRepository implements ThumbnailRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcThumbnailRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public List<Thumbnail> findBySessionId(long sessionId) {
        String sql = "select id, session_id, name, url, file_size, width_pixel, height_pixel from thumbnail where " +
                "session_id = ?";
        RowMapper<Thumbnail> rowMapper = (rs, rowNum) -> new Thumbnail(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getString(4),
                rs.getLong(5),
                rs.getLong(6),
                rs.getLong(7));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
