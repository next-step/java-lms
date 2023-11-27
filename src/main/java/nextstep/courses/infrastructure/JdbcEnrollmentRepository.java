package nextstep.courses.infrastructure;

import nextstep.courses.domain.EnrollmentRepository;
import nextstep.courses.domain.strategy.EnrollmentFactory;
import nextstep.courses.domain.strategy.EnrollmentStrategy;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentRepository implements EnrollmentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcEnrollmentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public EnrollmentStrategy findBySessionId(long sessionId) {
        String sql = "select id, session_id, enroll_type, capacity, amount from enrollment where session_id = ?";
        RowMapper<EnrollmentStrategy> rowMapper = (rs, rowNum) -> EnrollmentFactory.create(
                rs.getLong(1),
                rs.getLong(2),
                rs.getString(3),
                rs.getInt(4),
                rs.getLong(5)
        );

        return jdbcTemplate.queryForObject(sql, rowMapper, sessionId);
    }
}
