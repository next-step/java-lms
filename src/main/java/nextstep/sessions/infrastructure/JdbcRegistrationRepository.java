package nextstep.sessions.infrastructure;

import java.util.List;
import java.util.Optional;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;
import nextstep.payments.domain.Payment;
import nextstep.sessions.domain.data.registration.*;
import nextstep.sessions.repository.RegistrationRepository;
import nextstep.users.domain.NsUser;

@Repository("sessionRepository")
public class JdbcRegistrationRepository implements RegistrationRepository {

    private final JdbcOperations jdbcTemplate;

    public JdbcRegistrationRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Registration> findAllById(int sessionId) {
        String sql =
            "    select " +
                "  s.id, " +
                "  s.paid_type, " +
                "  s.fee, " +
                "  s.capacity, " +
                "  s.running_state, " +
                "  s.recruiting_state, " +
                "  s.start_date, " +
                "  s.end_date, " +
                "  u.id, " +
                "  u.user_id, " +
                "  u.password, " +
                "  u.name, " +
                "  u.email, " +
                "  r.id, " +
                "  r.selection_type, " +
                "  r.approval_type " +
                "from new_registration r " +
                "join new_session s " +
                "  on r.session_id = s.id " +
                "left join ns_user u " +
                "  on r.user_id = u.id " +
                "where r.session_id = ? ";

        RowMapper<Registration> rowMapper = registration();
        return jdbcTemplate.query(sql, rowMapper, sessionId);
    }

    @Override
    public void save(Registration registration) {
        String sql =
            "    insert into new_registration ( " +
                "  session_id, " +
                "  user_id, " +
                "  payment_id " +
                ") values ( " +
                "  ?, " +
                "  ?, " +
                "  ? " +
                ") ";

        jdbcTemplate.update(sql, registration.sessionId(), registration.userId(), registration.paymentId());
    }

    @Override
    public Optional<Registration> findById(int registrationId) {
        String sql =
            "    select " +
                "  s.id, " +
                "  s.paid_type, " +
                "  s.fee, " +
                "  s.capacity, " +
                "  s.running_state, " +
                "  s.recruiting_state, " +
                "  s.start_date, " +
                "  s.end_date, " +
                "  u.id, " +
                "  u.user_id, " +
                "  u.password, " +
                "  u.name, " +
                "  u.email, " +
                "  r.id, " +
                "  r.selection_type, " +
                "  r.approval_type " +
                "from new_registration r " +
                "join new_session s " +
                "  on r.session_id = s.id " +
                "left join ns_user u " +
                "  on r.user_id = u.id " +
                "where r.id = ? ";

        RowMapper<Registration> rowMapper = registration();
        try {
            return Optional.ofNullable(jdbcTemplate.queryForObject(sql, rowMapper, registrationId));
        } catch (EmptyResultDataAccessException e) {
            return Optional.empty();
        }
    }

    @Override
    public void updateSelectionType(Registration registration) {
        String sql =
            "    update new_registration " +
                "set selection_type = ? " +
                "where id = ? ";

        jdbcTemplate.update(sql, registration.selectionType().code(), registration.id());
    }

    @Override
    public void updateApprovalType(Registration registration) {
        String sql =
            "    update new_registration " +
                "set approval_type = ? " +
                "where id = ? ";

        jdbcTemplate.update(sql, registration.approvalType().code(), registration.id());
    }

    @Override
    public void deleteById(int registrationId) {
        String sql =
            "    delete from new_registration " +
                "where id = ? ";

        jdbcTemplate.update(sql, registrationId);
    }

    private static RowMapper<Registration> registration() {
        return (rs, rowNum) -> new Registration(
            JdbcSessionRepository.session(rs),
            new NsUser(rs.getLong(9),
                rs.getString(10),
                rs.getString(11),
                rs.getString(12),
                rs.getNString(13)),
            new Payment(),
            rs.getLong(14),
            SelectionType.valueOfCode(rs.getString(15)),
            ApprovalType.valueOfCode(rs.getString(16))
        );
    }
}
