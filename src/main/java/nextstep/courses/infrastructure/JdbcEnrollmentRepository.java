package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrolledStudent;
import nextstep.courses.domain.session.EnrollmentCandidate;
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

    @Override
    public void saveCandidate(EnrollmentCandidate candidate) {
        String sql = "insert into session_enrollment (session_id, ns_user_id, enrolled_at, enrollment_status) values(?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                candidate.getSessionId(),
                candidate.getNsUserId(),
                Timestamp.valueOf(LocalDateTime.now()),
                candidate.getStatus().getValue());
    }

    @Override
    public void updateCandidate(EnrollmentCandidate candidate) {
        String sql = "update session_enrollment set enrollment_status = ?, approved_at = ?, approved_by = ? " +
                "where session_id = ? and ns_user_id = ?";

        Timestamp approvedAt = candidate.getApprovedAt() != null
                ? Timestamp.valueOf(candidate.getApprovedAt())
                : null;

        jdbcTemplate.update(sql,
                candidate.getStatus().getValue(),
                approvedAt,
                candidate.getApprovedBy(),
                candidate.getSessionId(),
                candidate.getNsUserId());
    }
}
