package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.repository.StudentRepository;
import nextstep.courses.domain.session.student.Student;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcStudentRepository implements StudentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Student student) {
        String sql = "insert into student (session_id, ns_user_id) values(?, ?)";
        jdbcTemplate.update(sql, student.sessionId(), student.nsUserId());
    }
}
