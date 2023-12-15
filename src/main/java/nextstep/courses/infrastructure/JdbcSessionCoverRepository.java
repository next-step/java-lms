package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionCover;
import nextstep.courses.domain.repository.SessionCoverRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

import static nextstep.courses.utils.DateUtil.toLocalDateTime;

@Repository("sessionCoverRepository")
public class JdbcSessionCoverRepository implements SessionCoverRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcSessionCoverRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(SessionCover sessionCover) {
        String sql = "insert into session_cover (image, created_at, updated_at) values(?,?,?)";
        return jdbcTemplate.update(sql, sessionCover.image(), LocalDateTime.now(), LocalDateTime.now());
    }

    @Override
    public SessionCover findById(Long id) {
        String sql = "select id, image, created_at, updated_at from session_cover where id = ?";
        RowMapper<SessionCover> rowMapper = (rs, rowNum) -> new SessionCover(
                rs.getLong(1),
                rs.getBytes(2),
                toLocalDateTime(rs.getTimestamp(3)),
                toLocalDateTime(rs.getTimestamp(4)));

        return jdbcTemplate.queryForObject(sql, rowMapper, id);
    }
}
