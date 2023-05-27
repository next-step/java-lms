package nextstep.courses.infrastructure;

import nextstep.courses.domain.ragistration.Registration;
import nextstep.courses.domain.ragistration.RegistrationRepository;
import nextstep.courses.domain.ragistration.RegistrationType;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.sql.Timestamp;
import java.time.LocalDateTime;

@Repository
public class JdbcRegistrationRepository implements RegistrationRepository {
    private final JdbcOperations jdbcTemplate;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Registration registration) {
        String sql = "insert into registration (course_id, session_id, ns_user_id, registration_type,created_at) values(?,?,?,?,?)";
        return jdbcTemplate.update(
                sql,
                registration.getCourseId(),
                registration.getSessionId(),
                registration.getRegistrationType().name(),
                registration.getCreatedAt()
        );
    }

    @Override
    public Registration findByCourseIdAndSessionIdAndUserId(long courseId, long sessionId, long userId) {
        String sql = "select id, course_id, session_id, ns_user_id, registration_type,created_at,updated_at from registration where course_id = ? and session_id =? and ns_user_id = ?";
        RowMapper<Registration> rowMapper = ((rs, rowNum) -> new Registration(
                rs.getLong(1),
                rs.getLong(2),
                rs.getLong(3),
                rs.getLong(4),
                RegistrationType.valueOf(rs.getString(5)),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7))
        ));
        return jdbcTemplate.queryForObject(sql, rowMapper, courseId, sessionId, userId);
    }

    @Override
    public int update(Registration registration) {
        String sql = "update registration set registration_type= ? where id = ?";
        return jdbcTemplate.update(
                sql,
                registration.getId(),
                registration.getRegistrationType().name()
        );
    }

    private LocalDateTime toLocalDateTime(Timestamp timestamp) {
        if (timestamp == null) {
            return null;
        }
        return timestamp.toLocalDateTime();
    }
}
