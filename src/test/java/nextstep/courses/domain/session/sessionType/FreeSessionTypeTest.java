package nextstep.courses.domain.session.sessionType;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class FreeSessionTypeTest {
    FreeSessionType freeSessionType = new FreeSessionType();

    @Test
    @DisplayName("isSessionFull(): numberOfCurrentEnrollment에 상관 없이 항상 true를 반환한다.")
    void testIsSessionFull() {
        assertThat(freeSessionType.isSessionFull(10000)).isTrue();
    }

    @Test
    @DisplayName("isValidPayment(): Payment.amount에 상관 없이 true를 반환한다.")
    void testIsValidPayment() {
        Payment payment = new Payment();
        assertThat(freeSessionType.isValidPayment(payment)).isTrue();
    }
}