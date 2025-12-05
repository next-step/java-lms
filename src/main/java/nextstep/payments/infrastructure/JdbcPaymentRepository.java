package nextstep.payments.infrastructure;

import nextstep.payments.domain.Payment;
import nextstep.payments.repository.PaymentRepository;
import org.springframework.jdbc.core.JdbcOperations;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository("paymentRepository")
public class JdbcPaymentRepository implements PaymentRepository {
    private JdbcOperations jdbcTemplate;

    public JdbcPaymentRepository(JdbcOperations jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public int save(Payment payment) {
        String sql = "INSERT INTO payment (session_id, user_id, amount, created_at)" +
                " VALUES (?, ?, ?, ?)";

        return jdbcTemplate.update(sql,
                payment.getSessionId(),
                payment.getNsUserId(),
                payment.getAmount(),
                LocalDateTime.now()
                );
    }
}
