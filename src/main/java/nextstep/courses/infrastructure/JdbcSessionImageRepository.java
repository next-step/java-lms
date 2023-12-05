package nextstep.courses.infrastructure;

import nextstep.courses.domain.repository.SessionImage;
import nextstep.courses.domain.repository.SessionImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("sessionImageRepository")
public class JdbcSessionImageRepository implements SessionImageRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionImage sessionImage) {
        String sql = "insert into session_image (session_id, image_id) values(? , ?)";
        return jdbcTemplate.update(sql, sessionImage.sessionId(), sessionImage.imageId());
    }

    @Override
    public int findBySessionId(Long sessionId) {
        String sql = "select image_id from session_image where session_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, sessionId);
    }

    @Override
    public int findByImageId(Long imageId) {
        String sql = "select session_id from session_image where image_id = ?";
        return jdbcTemplate.queryForObject(sql, Integer.class, imageId);
    }
}
