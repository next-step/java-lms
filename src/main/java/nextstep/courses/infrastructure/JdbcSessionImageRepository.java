package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionImage;
import nextstep.courses.domain.SessionImageRepository;
import nextstep.courses.domain.SessionImages;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SessionImages findBy(Long sessionId) {
        String sql = "select image_size, image_extension, image_width, image_height " +
                "from session_image " +
                "where session_id = ?";
        RowMapper<SessionImage> rowMapper = (rs, rowNum) -> new SessionImage(
                rs.getInt(1),
                rs.getString(2),
                rs.getInt(3),
                rs.getInt(4)
        );
        List<SessionImage> sessionImages = jdbcTemplate.query(sql, rowMapper, sessionId);
        return new SessionImages(sessionImages);
    }
}

