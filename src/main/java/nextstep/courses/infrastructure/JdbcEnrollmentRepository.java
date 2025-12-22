package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.EnrollmentCandidate;
import nextstep.courses.domain.enrollment.EnrollmentRepository;
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
    public void save(EnrollmentCandidate candidate) {
        String sql = "insert into session_enrollment (session_id, ns_user_id, enrolled_at) values(?, ?, ?)";
        jdbcTemplate.update(sql, candidate.getSessionId(), candidate.getNsUserId(), Timestamp.valueOf(LocalDateTime.now()));
    }

    @Override
    public List<EnrollmentCandidate> findBySessionId(Long sessionId) {
        String sql = "select session_id, ns_user_id, enrollment_status from session_enrollment where session_id = ?";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> new EnrollmentCandidate(
                        rs.getLong("session_id"),
                        rs.getLong("ns_user_id")
                ), sessionId);
    }

//    @Override
//    public void saveCandidate(EnrollmentCandidate candidate) {
//        String sql = "insert into session_enrollment (session_id, ns_user_id, enrolled_at, enrollment_status) values(?, ?, ?, ?)";
//        jdbcTemplate.update(sql,
//                candidate.getSessionId(),
//                candidate.getNsUserId(),
//                Timestamp.valueOf(LocalDateTime.now()),
//                candidate.getStatus().getValue());
//    }

    @Override
    public void update(EnrollmentCandidate candidate) {
        String sql = "update session_enrollment set enrollment_status = ? " +
                "where session_id = ? and ns_user_id = ?";

        jdbcTemplate.update(sql,
                candidate.getStatus().getValue(),
                candidate.getSessionId(),
                candidate.getNsUserId());
    }
}
