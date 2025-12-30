package nextstep.courses.infrastructure;

import java.time.LocalDate;
import java.util.List;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;

public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into enrollment (student_id, session_id, created_at) values (?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.studentId(), enrollment.sessionId(), LocalDate.now());
    }

    @Override
    public Enrollment findById(Long id) {
        return null;
    }

    @Override
    public List<Enrollment> findBySessionId(Long sessionId) {
        return null;
    }
}
