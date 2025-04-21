package nextstep.payments.infrastructor;

import nextstep.courses.domain.session.Session;
import nextstep.payments.domain.Payment;
import nextstep.payments.domain.PaymentRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

@JdbcTest
class JdbcPaymentRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PaymentRepository paymentRepository;

    @BeforeEach
    void setUp() {
        paymentRepository = new JdbcPaymentRepository(jdbcTemplate);
    }

    @DisplayName("결재 저장")
    @Test
    void testSave() {
        Payment payment = new Payment(new Session("1"), NsUserTest.JAVAJIGI, 300_000L);
        assertDoesNotThrow(() -> paymentRepository.save(payment.toPaymentEntity()));
    }

    @DisplayName("결재 아이디로 조회")
    @Test
    void testFindById() {
        Payment payment = new Payment(new Session("1"), NsUserTest.JAVAJIGI, 300_000L);
        long savedId = paymentRepository.save(payment.toPaymentEntity());
        assertThat(paymentRepository.findById(savedId)).isNotNull();
    }

    @DisplayName("세션 아이디로 결재 정보 조회")
    @Test
    void testFindBySession() {
        Payment javajigiPayment = new Payment(new Session("2"), NsUserTest.JAVAJIGI, 300_000L);
        Payment sanjigiPayment = new Payment(new Session("2"), NsUserTest.SANJIGI, 300_000L);
        paymentRepository.save(javajigiPayment.toPaymentEntity());
        paymentRepository.save(sanjigiPayment.toPaymentEntity());

        assertThat(paymentRepository.findBySession(2L).size()).isEqualTo(2);
    }
}
