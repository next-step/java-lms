package nextstep.courses.infrastructure;

import nextstep.courses.domain.StudentRepository;
import nextstep.courses.domain.enrollment.Student;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcOperations) {
        this.jdbcTemplate = jdbcOperations;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into sessions_users (session_id, user_id) values(?, ?)";
        return jdbcTemplate.update(sql, student.getSessionId(), student.getNsUserId());
    }

    @Override
    public Student findById(Long studentId) {
        String sql = "select id, session_id"+
                " from " +
                " where id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) ->
                new Student(rs.getLong(1), rs.getLong(2));

        return jdbcTemplate.queryForObject(sql, rowMapper, studentId);
    }

    @Override
    public List<Student> findAllBySessionId(Long sessionId) {
        String sql = "select session_id, user_id " +
                "from sessions_users " +
                "where session_id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2)
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
