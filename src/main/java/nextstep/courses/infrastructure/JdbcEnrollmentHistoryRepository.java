package nextstep.courses.infrastructure;

import nextstep.courses.domain.enrollment.EnrollmentHistory;
import nextstep.courses.domain.enrollment.EnrollmentHistoryRepository;
import nextstep.courses.domain.enrollment.EnrollmentStatus;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.util.List;

@Repository("enrollmentHistoryRepository")
public class JdbcEnrollmentHistoryRepository implements EnrollmentHistoryRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcEnrollmentHistoryRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public void save(EnrollmentHistory history) {
        String sql = "insert into enrollment_history (session_id, ns_user_id, enrollment_status, changed_by, changed_at) " +
                "values(?, ?, ?, ?, ?)";
        jdbcTemplate.update(sql,
                history.getSessionId(),
                history.getNsUserId(),
                history.getStatus().getValue(),
                history.getChangedBy(),
                Timestamp.valueOf(history.getChangedAt()));
    }

    @Override
    public List<EnrollmentHistory> findBySessionIdAndUserId(Long sessionId, Long userId) {

        String sql = "select id, session_id, ns_user_id, enrollment_status, changed_by, changed_at " +
                "from enrollment_history " +
                "where session_id = ? and ns_user_id = ? " +
                "order by changed_at";
        return jdbcTemplate.query(sql,
                (rs, rowNum) -> {
                    EnrollmentHistory history = new EnrollmentHistory(
                            rs.getLong("session_id"),
                            rs.getLong("ns_user_id"),
                            EnrollmentStatus.from(rs.getString("enrollment_status")),
                            rs.getObject("changed_by", Long.class)
                    );
                    return history;
                },
                sessionId,
                userId);
    }
}
