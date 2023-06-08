package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

import static nextstep.courses.util.RepositoryUtils.toLocalDateTime;

public class JdbcStudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Student student) {
        String sql = "insert into sessions_users (session_id, user_id) values(?, ?)";
        return jdbcTemplate.update(sql, student.getSessionId(), student.getNsUserId());
    }

    public List<NsUser> findAllBySessionId(Long sessionId) {
        String sql = "select U.id, U.user_id, U.password, U.name, U.email, U.created_at, U.updated_at " +
                "from ns_user U " +
                "inner join sessions_users SU on U.id = SU.user_id where SU.session_id = ?";

        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
