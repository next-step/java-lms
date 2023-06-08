package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.Student;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcStudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    public int save(Student student) {
        String sql = "insert into sessions_users (session_id, user_id) values(?, ?)";
        return jdbcTemplate.update(sql, student.getSessionId(), student.getNsUserId());
    }

    public List<Student> findAllBySessionId(Long sessionId) {
        String sql = "select session_id, user_id from sessions_users where session_id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2)
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
