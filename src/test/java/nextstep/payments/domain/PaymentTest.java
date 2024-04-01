package nextstep.payments.domain;

import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

class PaymentTest {

    @Test
    void create() {
        LocalDateTime now = LocalDateTime.now();
        Payment payment = new Payment(0L, 1L, 1L, 1000L, now);
        assertThat(payment).isEqualTo(new Payment(0L, 1L, 1L, 1000L, now));
    }

    @Test
    void 강의아이디가_동일한지_확인한다() {
        Payment payment = new Payment(0L, 1L, 1L, 1000L);

        assertThat(payment.verifySessionId(1L)).isTrue();
        assertThat(payment.verifySessionId(0L)).isFalse();
    }

    @Test
    void 유저아이디가_동일한지_확인한다() {
        Payment payment = new Payment(0L, 1L, 1L, 1000L);

        assertThat(payment.verifyUserId(1L)).isTrue();
        assertThat(payment.verifyUserId(0L)).isFalse();
    }

    @Test
    void 결제금액과_강의비용이_동일한지_확인한다() {
        Payment payment = new Payment(0L, 1L, 1L, 1000L);

        assertThat(payment.verifySessionAmount(1000L)).isTrue();
        assertThat(payment.verifySessionAmount(999L)).isFalse();
        assertThat(payment.verifySessionAmount(1001L)).isFalse();
    }

}
