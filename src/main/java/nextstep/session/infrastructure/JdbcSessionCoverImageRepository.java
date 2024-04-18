package nextstep.session.infrastructure;

import nextstep.session.domain.SessionCoverImage;
import nextstep.session.domain.SessionCoverImageRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionCoverImageRepository")
public class JdbcSessionCoverImageRepository implements SessionCoverImageRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionCoverImageRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public SessionCoverImage findBySessionId(Long sessionId) {
        String sql = "select id,session_id, width, height, size, image_type from session_image where session_id = ?";
        RowMapper<SessionCoverImage> rowMapper = (rs, rowNum) -> new SessionCoverImage(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3),
            rs.getLong(4),
            rs.getLong(5),
            rs.getString(6)
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }

    @Override
    public int save(SessionCoverImage coverImage) {
        String sql = "insert into session_image(session_id, width, height, size, image_type) values(?,?,?,?,?)";
        return jdbcTemplate.update(sql, coverImage.getSession_id(), coverImage.getWidth(),
            coverImage.getHeight(), coverImage.getSize(), coverImage.getImageType());
    }
}
