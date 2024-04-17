package nextstep.courses.infrastructure;

import nextstep.courses.domain.image.Image;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.utils.LocalDateTimeUtils;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("ImageRepository")
public class JdbcImageRepository implements ImageRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Image> findBySession(Long sessionId) {
        String sql = "SELECT id, session_id, size, height, width, name, extension, created_at, updated_at from image where session_id = ?";

        RowMapper<Image> rowMapper = (rs, row) ->
                new Image(rs.getLong(1), null, rs.getInt(3),
                        rs.getInt(4), rs.getInt(5), rs.getString(6),
                        Image.FileExtension.from(rs.getString(7)), LocalDateTimeUtils.from(rs.getTimestamp(8)),
                        LocalDateTimeUtils.from(rs.getDate(9)));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
