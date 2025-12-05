package nextstep.payments.infrastructure;

import nextstep.payments.domain.Payment;
import nextstep.payments.repository.PaymentRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class PaymentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new JdbcPaymentRepository(jdbcTemplate);
    }

    @Test
    void crud(){
        int count = paymentRepository.save(new Payment(1L, 1L, 300_000L));
        assertThat(count).isEqualTo(1);
    }

}