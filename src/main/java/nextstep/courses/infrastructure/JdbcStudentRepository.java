package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Student;
import nextstep.courses.repository.StudentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;

import java.util.List;

public class JdbcStudentRepository implements StudentRepository {

    private final JdbcOperations jdbcOperations;

    public JdbcStudentRepository(JdbcOperations jdbcOperations) {
        this.jdbcOperations = jdbcOperations;
    }

    @Override
    public List<Student> findBySessionId(Long sessionId) {
        String sql = "select session_id, user_id from session_student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(rs.getLong(1), rs.getLong(2));
        return jdbcOperations.query(sql, rowMapper, sessionId);
    }

    @Override
    public int registerSession(Long sessionId, Long userId) {
        String sql = "insert into session_student (session_id, user_id) values (?, ?)";
        return jdbcOperations.update(sql, sessionId, userId);
    }
}
