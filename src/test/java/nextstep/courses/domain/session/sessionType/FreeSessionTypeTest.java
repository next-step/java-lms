package nextstep.courses.domain.session.sessionType;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FreeSessionTypeTest {
    public static final FreeSessionType FREE_SESSION_TYPE = new FreeSessionType();

    @Test
    @DisplayName("isEnrollmentPossible(): numberOfCurrentEnrollment와 Payment.amount에 상관 없이 항상 true를 반환한다.")
    void testIsSessionNotFull() {
        FreeSessionType freeSessionType = new FreeSessionType();
        assertThat(freeSessionType.isEnrollmentPossible(100, new Payment())).isTrue();
    }
}
