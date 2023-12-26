package nextstep.session.infrastructure;

import nextstep.session.domain.Enrollment;
import nextstep.session.domain.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.util.List;

import static nextstep.common.domain.utils.JdbcConvertUtils.toLocalDateTime;

@Repository
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(Enrollment enrollment) {
        String sql = "insert into session_enrollment (created_at, updated_at, user_id, session_id, approved) values(?,?,?,?,?)";
        jdbcTemplate.update(sql, enrollment.getCreatedAt(), enrollment.getUpdatedAt(), enrollment.getStudentId(), enrollment.getSessionId(), enrollment.isApproved());
    }

    @Override
    public List<Enrollment> findAllBySessionId(Long sessionId) {
        String sql = "select id, created_at, updated_at, user_id, session_id, approved from session_enrollment where session_id = ?";
        return jdbcTemplate.query(sql, (rs, rowNum) -> new Enrollment(
                rs.getLong(1),
                toLocalDateTime(rs.getTimestamp(2)),
                toLocalDateTime(rs.getTimestamp(3)),
                rs.getLong(4),
                rs.getLong(5),
                rs.getBoolean(6)
        ), sessionId);
    }
}
