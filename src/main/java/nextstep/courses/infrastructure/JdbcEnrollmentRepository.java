package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrollmentRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Set;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Set<NsUser> findEnrolledUsersBySessionId(Long sessionId) {
        String sql = "SELECT us.* FROM enrollment e JOIN ns_user us ON e.user_id = us.id WHERE session_id = ?";
        List<NsUser> enrolledUsers = jdbcTemplate.query(sql, userRowMapper, sessionId);
        return new HashSet<>(enrolledUsers);
    }

    private final RowMapper<NsUser> userRowMapper = (rs, rowNum) -> new NsUser(
            rs.getLong("id"),
            rs.getString("user_id"),
            rs.getString("password"),
            rs.getString("name"),
            rs.getString("email"),
            rs.getTimestamp("created_at").toLocalDateTime(),
            rs.getTimestamp("updated_at") != null ? rs.getTimestamp("updated_at").toLocalDateTime() : null
    );

    @Override
    public void save(Long sessionId, NsUser user) {
        String sql = "INSERT INTO enrollment (session_id, user_id) VALUES (?, ?)";
        jdbcTemplate.update(sql, sessionId, user.getId());
    }
}