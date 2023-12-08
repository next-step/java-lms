package nextstep.sessions.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.type.ApprovalType;
import nextstep.sessions.domain.data.type.SelectionType;
import nextstep.sessions.domain.data.vo.Registration;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.users.domain.NsUser;

@Repository("sessionRepository")
public class JdbcNewRegistrationRepository implements RegistrationRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcNewRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Registration> findAllById(int sessionId) {
        String sql = "select s.paid_type, s.fee, s.capacity, s.running_state, s.recruiting_state, s.start_date, s.end_date, u.id, u.user_id, u.password, u.name, u.email, r.selection_type, r.approval_type from new_registration r join new_session s on r.session_id = s.id left join ns_user u on r.user_id = u.id where r.session_id = ?";
        RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            JdbcNewSessionRepository.session(rs),
            new NsUser(rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getNString(12)),
            new Payment(),
            SelectionType.valueOfCode(rs.getString(13)),
            ApprovalType.valueOfCode(rs.getString(14))
        );
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public void save(int sessionId, Registration registration) {
        String sql = "insert into new_registration (session_id, user_id, payment_id) values(?, ?, ?)";
        jdbcTemplate.update(sql, sessionId, registration.userId(), registration.paymentId());
    }

    @Override
    public Optional<Registration> findById(int registrationId) {
        String sql = "select s.paid_type, s.fee, s.capacity, s.running_state, s.recruiting_state, s.start_date, s.end_date, u.id, u.user_id, u.password, u.name, u.email, r.selection_type, r.approval_type from new_registration r join new_session s on r.session_id = s.id left join ns_user u on r.user_id = u.id where r.id = ?";
        RowMapper<Registration> rowMapper = (rs, rowNum) -> new Registration(
            JdbcNewSessionRepository.session(rs),
            new NsUser(rs.getLong(8), rs.getString(9), rs.getString(10), rs.getString(11), rs.getNString(12)),
            new Payment(),
            SelectionType.valueOfCode(rs.getString(13)),
            ApprovalType.valueOfCode(rs.getString(14))
        );
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, registrationId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateSelectionType(int registrationId, SelectionType selectionType) {
        String sql = "update new_registration set selection_type = ? where id = ?";
        jdbcTemplate.update(sql, selectionType.code(), registrationId);

    }

    @Override
    public void updateApprovalType(int registrationId, ApprovalType approvalType) {
        String sql = "update new_registration set approval_type = ? where id = ?";
        jdbcTemplate.update(sql, approvalType.code(), registrationId);
    }

    @Override
    public void deleteById(int registrationId) {
        String sql = "delete from new_registration where id = ?";
        jdbcTemplate.update(sql, registrationId);
    }

}
