package nextstep.courses.infrastructure;

import nextstep.courses.domain.Student;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Repository
public class JdbcStudentRepository {

    private final JdbcTemplate jdbcTemplate;

    @Transactional
    public int save(Student student) {
        final String sql = "insert into student (session_id, user_id, status) " +
                "values (?, ?, ?)";

        return jdbcTemplate.update(sql,
                student.getSessionId(),
                student.getUserId(),
                student.getStatus().name()
        );
    }

    @Transactional(readOnly = true)
    public List<Student> findBySessionId(Long sessionId) {
        final String sql = "select id, session_id, user_id, status " +
                "from student " +
                "where session_id = ?";

        return jdbcTemplate.queryForList(sql, sessionId)
                .stream()
                .map(e -> new Student(
                        (Long) e.get("id"),
                        (Long) e.get("user_id"),
                        (Long) e.get("session_id"),
                        Student.StudentStatus.valueOf((String) e.get("status"))
                )).collect(Collectors.toList());
    }

    public JdbcStudentRepository(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
