package nextstep.courses.infrastructure;

import nextstep.courses.domain.Enrollment;
import nextstep.courses.domain.EnrollmentStatus;
import nextstep.courses.domain.Member;
import nextstep.courses.domain.Session;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class JdbcEnrollmentRepository {

    private final JdbcOperations jdbc;
    private final JdbcSessionRepository jdbcSessionRepository;

    public JdbcEnrollmentRepository(JdbcOperations jdbc, JdbcSessionRepository jdbcSessionRepository) {
        this.jdbc = jdbc;
        this.jdbcSessionRepository = jdbcSessionRepository;
    }

    public void save(Long sessionId, Long memberId, EnrollmentStatus status) {
        String sql = "INSERT INTO enrollment (session_id, member_id, status) VALUES (?, ?, ?)";
        jdbc.update(sql, sessionId, memberId, status.name());
    }

    public void updateStatus(Long sessionId, Long memberId, EnrollmentStatus status) {
        String sql = "UPDATE enrollment SET status = ? WHERE session_id = ? AND member_id = ?";
        jdbc.update(sql, status.name(), sessionId, memberId);
    }

    public List<Enrollment> findBySessionId(Long sessionId) {
        String sql =
                "SELECT e.*, m.id as member_id, m.name, m.email " +
                        "FROM enrollment e " +
                        "JOIN member m ON e.member_id = m.id " +
                        "WHERE e.session_id = ?";


        return jdbc.query(sql, (rs, rowNum) -> {
            Member member = new Member(
                    rs.getLong("member_id"),
                    rs.getString("name"),
                    rs.getString("email")
            );

            Session session = jdbcSessionRepository.findById(sessionId);
            Enrollment enrollment = new Enrollment(member, session);
            EnrollmentStatus status = EnrollmentStatus.valueOf(rs.getString("status"));
            if (status == EnrollmentStatus.APPROVED) {
                enrollment.approve();
            } else if (status == EnrollmentStatus.REJECTED) {
                enrollment.reject();
            }

            return enrollment;
        }, sessionId);
    }
}
