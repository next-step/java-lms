package nextstep.sessions.infrastructure;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.Session;
import nextstep.sessions.domain.data.type.PaidType;
import nextstep.sessions.domain.data.type.SessionState;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.users.domain.NsUser;

import static nextstep.sessions.infrastructure.JdbcSessionRepository.toLocalDateTime;

@Repository("sessionRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Registration> findAllRegistrations(int sessionId) {
        String sql = "select s.paid_type, s.fee, s.capacity, s.state, s.start_date, s.end_date, u.id, u.user_id, u.password, u.name, u.email from registration r join session s on r.session_id = s.id left join ns_user u on r.user_id = u.id where r.session_id = ?";
        RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            session(rs),
            new NsUser(rs.getLong(7), rs.getString(8), rs.getString(9), rs.getString(10), rs.getNString(11)),
            new Payment()
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    private Session session(ResultSet rs) throws SQLException {
        return new Session(
            PaidType.valueOf(rs.getString(1)),
            rs.getLong(2),
            rs.getInt(3),
            SessionState.valueOf(rs.getString(4)),
            toLocalDateTime(rs.getTimestamp(5)),
            toLocalDateTime(rs.getTimestamp(6))
        );
    }

    @Override
    public void saveRegistration(int sessionId, Registration registration) {
        String sql = "insert into registration (session_id, user_id, payment_id) values(?, ?, ?)";
        jdbcTemplate.update(sql, sessionId, registration.userId(), registration.paymentId());
    }

}
