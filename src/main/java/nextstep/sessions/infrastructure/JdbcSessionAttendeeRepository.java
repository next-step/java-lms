package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionAttendeeRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("sessionAttendeeRepository")
public class JdbcSessionAttendeeRepository implements SessionAttendeeRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionAttendeeRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Session session, NsUser user) {
        String sql = "insert into session_attendee (session_id, user_id) values(?, ?)";
        return jdbcTemplate.update(sql, session.getId(), user.getId());
    }

    @Override
    public List<String> findBySessionId(Long sessionId) {
        String sql = "select user_id from session_attendee where session_id = ?";
        return jdbcTemplate.queryForList(sql, String.class, sessionId);
    }
}
