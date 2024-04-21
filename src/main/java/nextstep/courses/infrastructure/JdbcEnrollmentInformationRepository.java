package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.EnrollmentInformation;
import nextstep.courses.domain.session.EnrollmentInformationRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

@Repository("enrollmentRepository")
public class JdbcEnrollmentInformationRepository implements EnrollmentInformationRepository {

    private JdbcOperations jdbcTemplates;
    public JdbcEnrollmentInformationRepository(JdbcOperations jdbcTemplates) {
        this.jdbcTemplates = jdbcTemplates;
    }

    @Override
    public int save(EnrollmentInformation enrollmentInformation) {
        String sql = "INSERT INTO enrollment_information " +
                "(session_id, user_id, has_paid)" +
                "values(?,?,?)";

        return jdbcTemplates.update(
                sql,
                enrollmentInformation.getSessionId(),
                enrollmentInformation.getUserId(),
                enrollmentInformation.hasPaid()
        );
    }

    @Override
    public EnrollmentInformation findBySessionIdAndUserId(Long sessionId, Long userId) {
        String sql = "SELECT id, session_id, user_id, has_paid " +
                "FROM enrollment_information " +
                "WHERE session_id = ? AND user_id = ?";

        RowMapper<EnrollmentInformation> rowMapper = (rs, rowNum) -> {
            Long id = rs.getLong("id");
            boolean hasPaid = rs.getBoolean("has_paid");

            return EnrollmentInformation.of(id, sessionId, userId, hasPaid);
        };

        return jdbcTemplates.queryForObject(sql, rowMapper, sessionId, userId);
    }
}
