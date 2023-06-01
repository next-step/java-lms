package nextstep.infrastructure;

import nextstep.sessions.domain.SessionStudent;
import nextstep.sessions.domain.SessionStudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.util.List;

@Repository("sessionStudentRepository")
public class JdbcStudentRepository implements SessionStudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void takeSession(Long sessionId, Long nsUserId) {
        String sql = "insert into session_student (session_id, ns_user_id) values(?, ?)";
        jdbcTemplate.update(sql, sessionId, nsUserId);
    }

    @Override
    public List<SessionStudent> getStudents(Long sessionId) {
        String sql = "select id, session_id, ns_user_id from session_student WHERE session_id = ?";
        RowMapper<SessionStudent> rowMapper = (rs, rowNum) -> new SessionStudent(
                rs.getLong("id"),
                rs.getLong("session_id")
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
