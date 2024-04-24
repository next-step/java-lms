package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.Enrollment;
import nextstep.courses.domain.session.EnrollmentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {

    private JdbcOperations jdbcTemplates;
    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplates) {
        this.jdbcTemplates = jdbcTemplates;
    }

    @Override
    public int save(Enrollment enrollment) {
        String sql = "INSERT INTO enrollment " +
                "(session_id, user_id, has_paid)" +
                "values(?,?,?)";

        return jdbcTemplates.update(
                sql,
                enrollment.getSessionId(),
                enrollment.getUserId(),
                enrollment.hasPaid()
        );
    }

    @Override
    public Enrollment findBySessionIdAndUserId(Long sessionId, Long userId) {
        String sql = "SELECT id, session_id, user_id, has_paid " +
                "FROM enrollment " +
                "WHERE session_id = ? AND user_id = ?";

        RowMapper<Enrollment> rowMapper = (rs, rowNum) -> {
            Long id = rs.getLong("id");
            boolean hasPaid = rs.getBoolean("has_paid");

            return Enrollment.of(id, sessionId, userId, hasPaid);
        };

        return jdbcTemplates.queryForObject(sql, rowMapper, sessionId, userId);
    }
}
