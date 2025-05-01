package nextstep.payments.infrastructor;

import nextstep.payments.domain.PaymentRepository;
import nextstep.payments.entity.PaymentEntity;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

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
        PaymentEntity paymentEntity = createPaymentEntity(null, "1", 1L);
        assertDoesNotThrow(() -> paymentRepository.save(paymentEntity));
    }

    @DisplayName("결재 아이디로 조회")
    @Test
    void testFindById() {
        PaymentEntity paymentEntity = createPaymentEntity(null, "1", 1L);
        long savedId = paymentRepository.save(paymentEntity);
        assertThat(paymentRepository.findById(savedId)).isNotNull();
    }

    @DisplayName("세션 아이디로 결재 정보 조회")
    @Test
    void testFindBySession() {
        PaymentEntity paymentEntity1 = createPaymentEntity(null, "1", 2L);
        PaymentEntity paymentEntity2 = createPaymentEntity(null, "1", 2L);
        paymentRepository.save(paymentEntity1);
        paymentRepository.save(paymentEntity2);

        assertThat(paymentRepository.findBySession(2L).size()).isEqualTo(2);
    }

    @DisplayName("결재 상태 업데이트")
    @Test
    void testUpdateStatus() {
        PaymentEntity paymentEntity = createPaymentEntity(null, "1", 1L);
        long savedId = paymentRepository.save(paymentEntity);

        String newStatus = "APPROVED";
        paymentRepository.updateStatus(savedId, newStatus);

        PaymentEntity updatedPaymentEntity = paymentRepository.findById(savedId);
        assertThat(updatedPaymentEntity.getStatus()).isEqualTo(newStatus);
    }

    private PaymentEntity createPaymentEntity(Long id, String userId, Long sessionId) {
        return PaymentEntity.builder()
            .id(id)
            .userId(userId)
            .sessionId(sessionId)
            .amount(300_000L)
            .status("대기중")
            .createdAt(LocalDateTime.now())
            .updatedAt(LocalDateTime.now())
            .deleted(false)
            .build();
    }
}
