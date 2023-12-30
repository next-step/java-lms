package nextstep.courses.infrastructure;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import nextstep.courses.domain.EnrollmentRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUsers;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Long userId, Long sessionId) {
        String sql = "INSERT INTO enrollment (user_id, session_id, created_at) VALUES (?, ?, ?)";
        return jdbcTemplate.update(sql, userId, sessionId, LocalDateTime.now());
    }

    @Override
    public NsUsers findBySessionId(Long id) {
        String sql = "select u.id, u.user_id, u.password, "
                + "u.name, u.email, u.created_at, u.updated_at "
                + "from ns_user u "
                + "join enrollment e on u.id = e.user_id "
                + "where session_id = ?";
        return new NsUsers(jdbcTemplate.query(sql,
                (rs, rowNum) -> new NsUser(
                        rs.getLong("id"),
                        rs.getString("user_id"),
                        rs.getString("password"),
                        rs.getString("name"),
                        rs.getString("email"),
                        toLocalDateTime(rs.getTimestamp("created_at")),
                        toLocalDateTime(rs.getTimestamp("updated_at"))
                ),
                id
        ));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
