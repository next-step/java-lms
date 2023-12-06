package nextstep.sessions.infrastructure;

import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.users.domain.NsUser;

@Repository("sessionRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Registration> findAllRegistrations(int sessionId) {
        String sql = "select s.paid_type, s.fee, s.capacity, s.running_state, s.recruiting_state, s.start_date, s.end_date, u.id, u.user_id, u.password, u.name, u.email from registration r join new_session s on r.session_id = s.id left join ns_user u on r.user_id = u.id where r.session_id = ?";
        RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            JdbcNewSessionRepository.session(rs),
            new NsUser(rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getNString(12)),
            new Payment()
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public void saveRegistration(int sessionId, Registration registration) {
        String sql = "insert into registration (session_id, user_id, payment_id) values(?, ?, ?)";
        jdbcTemplate.update(sql, sessionId, registration.userId(), registration.paymentId());
    }

}
