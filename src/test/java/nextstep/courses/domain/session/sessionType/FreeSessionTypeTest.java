package nextstep.courses.domain.session.sessionType;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeSessionTypeTest {
    public static final FreeSessionType FREE_SESSION_TYPE = new FreeSessionType();

    @Test
    @DisplayName("isSessionNotFull(): numberOfCurrentEnrollment에 상관 없이 항상 true를 반환한다.")
    void testIsSessionNotFull() {
        FreeSessionType freeSessionType = new FreeSessionType();
        assertThat(freeSessionType.isSessionNotFull(10000)).isTrue();
    }

    @Test
    @DisplayName("isValidPayment(): Payment.amount에 상관 없이 true를 반환한다.")
    void testIsValidPayment() {
        Payment payment = new Payment();
        FreeSessionType freeSessionType = new FreeSessionType();
        assertThat(freeSessionType.isValidPayment(payment)).isTrue();
    }
}
