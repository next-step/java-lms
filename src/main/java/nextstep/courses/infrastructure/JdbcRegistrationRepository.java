package nextstep.courses.infrastructure;

import nextstep.courses.domain.Registration;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.repository.RegistrationRepository;
import nextstep.users.domain.NsUser;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.utils.DateUtil.toLocalDateTime;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public int save(Registration registration) {
        String sql = "insert into registration (ns_user_id, session_id, amount, created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, registration.nsUser().getId(), registration.session().id(), registration.amount(), LocalDateTime.now());
    }

    @Override
    public List<Registration> findAllBySessionId(Long sessionId) {
        String sql = "select id, session_id, ns_user_id, amount, created_at, updated_at " +
                "from registration r where session_id = ?";

        RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
                rs.getLong(1),
                new NsUser(rs.getLong(2)),
                new Session(rs.getLong(3)),
                rs.getLong(4),
                toLocalDateTime(rs.getTimestamp(5)),
                toLocalDateTime(rs.getTimestamp(6)));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
