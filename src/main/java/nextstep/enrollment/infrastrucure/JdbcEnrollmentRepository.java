package nextstep.enrollment.infrastrucure;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import nextstep.enrollment.domain.Enrollment;
import nextstep.enrollment.domain.EnrollmentRepository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(final JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(final Enrollment enrollment) {
        final String sql = "insert into enrollment (enrollment_status, user_id, session_id) values (?, ?, ?)";
        return jdbcTemplate.update(sql, enrollment.getStatus().name(), enrollment.getAttendeeId(), enrollment.getSession());
    }
}
