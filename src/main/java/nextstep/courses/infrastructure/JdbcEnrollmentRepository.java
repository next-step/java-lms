package nextstep.courses.infrastructure;

import java.time.LocalDateTime;
import java.util.List;
import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into enrollment (student_id, session_id, created_at) values (?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.studentId(), enrollment.sessionId(), LocalDateTime.now());
    }

    @Override
    public Enrollment findById(Long id) {
        String sql = "select id, student_id, session_id from enrollment where id = ?";
        return jdbcTemplate.queryForObject(sql, rowMapper(), id);
    }

    @Override
    public List<Enrollment> findBySessionId(Long sessionId) {
        String sql = "select id, student_id, session_id from enrollment where session_id = ?";
        return jdbcTemplate.query(sql, rowMapper(), sessionId);
    }

    private RowMapper<Enrollment> rowMapper() {
        return (rs, rowNum) -> new Enrollment(
                rs.getLong("id"),
                rs.getLong("student_id"),
                rs.getLong("session_id"));
    }
}
