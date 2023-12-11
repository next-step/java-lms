package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.Students;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.HashSet;
import java.util.List;

public class JdbcStudentsRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Session session, Students students) {
        String sql = "insert into session_student (session_id, ns_user_id) values (?, ?)";
        List<NsUser> userList = session.getStudents().excludeNsUser(students);
        int count = 0;
        for (NsUser nsUser : userList) {
            count += jdbcTemplate.update(sql, session.getId(), nsUser.getId());
        }
        return count;
    }

    public Students findBySessionId(Long id) {
        String sql = "select ns_user.id, user_id, password, name, email, created_at, updated_at from ns_user join session_student on ns_user.id = session_student.ns_user_id " +
                "where session_student.session_id = ?";
        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        );
        List<NsUser> nsUserList = jdbcTemplate.query(sql, rowMapper, id);
        return new Students(new HashSet<>(nsUserList));
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
