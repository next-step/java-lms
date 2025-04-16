package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertThrows;

class PaymentPolicyTest {
    @Test
    @DisplayName("무료 강의는 최대 수강 인원이 0이어야 함")
    void freeSession_enrollmentMustBeZero() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PaymentPolicy(PaymentType.FREE, 0L, 1);
        });
    }

    @Test
    @DisplayName("유료 강의는 최소 수강인원이 1 이상이어야 한다.")
    void paidSession_haveLimit() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PaymentPolicy(PaymentType.PAID, 800_000L, 0);
        });
    }
}