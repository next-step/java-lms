package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Long sessionId, Long nsUserId) {
        String sql = "insert into session_enrollment (session_id, ns_user_id, enrolled_at) values(?, ?, ?)";
        jdbcTemplate.update(sql, sessionId, nsUserId, Timestamp.valueOf(LocalDateTime.now()));

    }
}
