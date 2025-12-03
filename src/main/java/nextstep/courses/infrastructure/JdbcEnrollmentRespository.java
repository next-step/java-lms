package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.repository.EnrollmentRepository;
import nextstep.courses.record.EnrollmentRecord;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRespository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRespository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "insert into enrollment (session_id, user_id) values (?, ?)";
        return jdbcTemplate.update(sql, enrollment.getSessionId(), enrollment.getUser().getId());
    }

    @Override
    public List<EnrollmentRecord> findBySessionId(Long sessionId) {
        String sql = "select * from enrollment where session_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            return new EnrollmentRecord(
                    rs.getLong("id"),
                    rs.getLong("user_id"),
                    rs.getLong("session_id")
            );
        }, sessionId);
    }
}
