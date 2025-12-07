package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrolledStudent;
import nextstep.courses.domain.session.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.List;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(EnrolledStudent enrolledStudent) {
        String sql = "insert into session_enrollment (session_id, ns_user_id, enrolled_at) values(?, ?, ?)";
        jdbcTemplate.update(sql, enrolledStudent.getSessionId(), enrolledStudent.getNsUserId(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public List<EnrolledStudent> findBySessionId(Long sessionId) {
        String sql = "select session_id, ns_user_id from session_enrollment where session_id = ?";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new EnrolledStudent(
                        rs.getLong("session_id"),
                        rs.getLong("ns_user_id")
                ), sessionId);
    }
}
