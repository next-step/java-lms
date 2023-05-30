package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionRepository;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcSessionRepository implements SessionRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcSessionRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session) {
        String sql = "insert into session() values()";
        return 0;
    }

    @Override
    public Session findById(Long id) {
        return null;
    }

    @Override
    public int update(Session session) {
        return 0;
    }

    @Override
    public int delete(Session session) {
        return 0;
    }
}
