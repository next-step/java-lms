package nextstep.courses.infrastructure;

import nextstep.courses.domain.SessionStudents;
import nextstep.courses.domain.SessionStudentsRepository;
import nextstep.courses.domain.Student;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionStudentsRepository")
public class JdbcSessionStudentsRepository implements SessionStudentsRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcSessionStudentsRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into students (session_id, student_Id) values(?, ?)";

        return jdbcTemplate.update(sql, student.getSessionId(), student.getSessionId());
    }

    @Override
    public SessionStudents findBySessionId(Long sessionId) {
        String sql = "select id, session_id, student_id from students where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3));

        return new SessionStudents(jdbcTemplate.query(sql, rowMapper, sessionId));
    }
}
