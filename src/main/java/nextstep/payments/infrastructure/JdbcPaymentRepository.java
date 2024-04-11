package nextstep.payments.infrastructure;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

@Repository("paymentRepository")
public class JdbcPaymentRepository implements PaymentRepository {

  private JdbcOperations jdbcTemplate;

  public JdbcPaymentRepository(JdbcOperations jdbcTemplate) {
    this.jdbcTemplate = jdbcTemplate;
  }

  @Override
  public Payment findBySessionAndUser(Long sessionId, Long userId) {
    String sql = "select id, session_id, user_id, amount from payment where session_id = ? and user_id = ?";
    return jdbcTemplate.queryForObject(sql, (rs, rowNum) ->
        new Payment(rs.getLong("ID"), rs.getLong("SESSION_ID"), rs.getLong("USER_ID"), rs.getLong("AMOUNT")),
        sessionId, userId);
  }
}
