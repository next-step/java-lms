package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Registration;
import nextstep.courses.domain.RegistrationRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.UserRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.courses.utils.DateUtil.toLocalDateTime;

@Repository("registrationRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {
    private JdbcOperations jdbcTemplate;
    private final UserRepository userRepository;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate, UserRepository userRepository) {
        this.jdbcTemplate = jdbcTemplate;
        this.userRepository = userRepository;
    }

    @Override
    public int save(Registration registration) {
        String sql = "insert into registration (ns_user_id, session_id, amount created_at) values(?, ?, ?, ?)";
        return jdbcTemplate.update(sql, registration.nsUser().getId(), registration.session().id(), registration.amount(), LocalDateTime.now());
    }

    @Override
    public List<NsUser> findParticipantsBySessionId(Long sessionId) {
        String sql = "select u.id, u.user_id, u.password, u.name, u.email, u.created_at, u.updated_at  " +
                "from registration r " +
                "join session s on r.session_id = s.id " +
                "join ns_user u on u.id = r.ns_user_id " +
                "where session_id = ?";

        RowMapper<NsUser> rowMapper = (rs, rowNum) -> new NsUser(
                rs.getLong(1),
                rs.getString(2),
                rs.getString(3),
                rs.getString(4),
                rs.getString(5),
                toLocalDateTime(rs.getTimestamp(6)),
                toLocalDateTime(rs.getTimestamp(7)));

        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }
}
