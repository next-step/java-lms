package nextstep.courses.infrastructure;

import nextstep.courses.domain.Thumbnail;
import nextstep.courses.domain.ThumbnailRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("thumbnailRepository")
public class JdbcThumbnailRepository implements ThumbnailRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcThumbnailRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Thumbnail findById(Integer id) {
        String sql = "select id, name, size, width, height from thumbnail where id = ?";
        RowMapper<Thumbnail> rowMapper = (rs, rowNum) -> new Thumbnail(
                rs.getInt(1),
                rs.getString(2),
                rs.getLong(3),
                rs.getInt(4),
                rs.getInt(5));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
