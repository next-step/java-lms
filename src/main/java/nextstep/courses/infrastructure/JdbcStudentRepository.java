package nextstep.courses.infrastructure;

import nextstep.courses.domain.StudentRepository;
import nextstep.courses.domain.registration.Student;
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
        String sql = "insert into student (user_id, session_id) values(?, ?)";
        return jdbcTemplate.update(sql, student.getNsUserId(), student.getSessionId());
    }

    @Override
    public Student findById(Long studentId) {
        String sql = "select user_id, session_id"+
                " from student" +
                " where user_id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) ->
                new Student(rs.getLong(1), rs.getLong(2));

        return jdbcTemplate.queryForObject(sql, rowMapper, studentId);
    }

    @Override
    public List<Student> findAllBySessionId(Long sessionId) {
        String sql = "select user_id, session_id " +
                "from student " +
                "where session_id = ?";

        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2)
        );

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
