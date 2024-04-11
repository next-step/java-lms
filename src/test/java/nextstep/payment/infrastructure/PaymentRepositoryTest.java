package nextstep.payment.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.infrastructure.JdbcPaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
public class PaymentRepositoryTest {

  @Autowired
  private JdbcTemplate jdbcTemplate;

  private PaymentRepository paymentRepository;

  @BeforeEach
  void setUp() {
    paymentRepository = new JdbcPaymentRepository(jdbcTemplate);
  }

  @Test
  @DisplayName("저장된 Payment Entity를 조회한다.")
  void findBySessionAndUser() {
    Payment payment = paymentRepository.findBySessionAndUser(5L, 1L);
    assertThat(payment.getId()).isEqualTo(20L);
  }
}
