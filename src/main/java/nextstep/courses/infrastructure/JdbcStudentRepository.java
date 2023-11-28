package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import nextstep.courses.domain.StudentRepository;
import nextstep.courses.domain.Students;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into student (user_id, session_id) values(?, ?)";

        return jdbcTemplate.update(sql, student.getNsUserId(), student.getSessionId());
    }

    @Override
    public Students findBySessionId(long sessionId) {
        String sql = "select ns_user_id, session_id, approved from student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                toBoolean(rs.getString(3)));

        return new Students(jdbcTemplate.query(sql, rowMapper, sessionId));
    }

    private boolean toBoolean(String approved) {
        return approved.equals("Y");
    }
}
