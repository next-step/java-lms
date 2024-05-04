package nextstep.sessions.infrastructure;

import nextstep.sessions.domain.Student;
import nextstep.sessions.domain.StudentRepository;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository("studentRepository")
public class JdbcStudentRepository implements StudentRepository {

    private final JdbcTemplate jdbcTemplate;

    public JdbcStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Student student) {
        String sql = "insert into students(ns_user_id, session_id) values(?, ?)";
        return jdbcTemplate.update(sql, student.getNsUserId(), student.getSessionId());
    }

    @Override
    public List<Student> findBySessionId(long sessionId) {
        String sql = "select * from students where session_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) ->
                        new Student(rs.getLong("ns_user_id"), rs.getLong("session_id"))
                , sessionId);
    }

    @Override
    public Optional<Student> findById(Long studentId) {
        String sql = "select * from students where id = ?";
        return Optional.ofNullable(jdbcTemplate.queryForObject(sql, (rs, rowNum) -> {
            long nsUserId = rs.getLong("ns_user_id");
            long sessionId = rs.getLong("session_id");
            return new Student(nsUserId, sessionId);
        }, studentId));
    }
}
