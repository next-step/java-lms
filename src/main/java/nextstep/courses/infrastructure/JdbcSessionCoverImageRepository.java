package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionCoverImage;
import nextstep.courses.domain.session.SessionCoverImageRepository;
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
    public int save(SessionCoverImage sessionCoverImage, Long sessionId) {
        String sql = "INSERT INTO session_cover_image ( session_id, path, name )"
                + "VALUES (?, ?, ?)";

        return jdbcTemplate.update(sql, sessionId, sessionCoverImage.getPath(), sessionCoverImage.getName());
    }

    @Override
    public SessionCoverImage findById(Long id) {
        String sql = "SELECT id, session_id, path, name FROM session_cover_image WHERE id = ?";
        RowMapper<SessionCoverImage> rowMapper = (rs, rowNum) -> new SessionCoverImage(
                rs.getLong(1),
                rs.getString(3),
                rs.getString(4)
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
