package nextstep.session.infrastructure;

import java.util.List;
import nextstep.session.domain.SessionStudentRepository;
import nextstep.session.domain.Student;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("sessionStudentRepository")
public class JdbcSessionStudentRepository implements SessionStudentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcSessionStudentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Student> findAllEnrolledInSession(Long sessionId) {
        String sql = "select id, user_id, session_id from session_student where session_id = ?";
        RowMapper<Student> rowMapper = (rs, rowNum) -> new Student(
            rs.getLong(1),
            rs.getLong(2),
            rs.getLong(3)
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public int save(Student student) {
        String sql = "insert into session_student(user_id, session_id) values(?,?)";
        return jdbcTemplate.update(sql, student.getUser_id(), student.getSession_id());
    }
}
