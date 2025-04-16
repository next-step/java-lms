package nextstep.payments.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertThrows;

class PaidPaymentPolicyTest {
    @Test
    @DisplayName("유료 강의는 최소 수강인원이 1 이상이어야 한다.")
    void paidSession_need1moreStudents() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PaidPaymentPolicy( 800_000L, 0);
        });
    }

    @Test
    @DisplayName("유료 강의는 수강료가 0원 이상이어야 합니다.")
    void paidSession_haveFee() {
        assertThrows(IllegalArgumentException.class, () -> {
            new PaidPaymentPolicy( 0L, 1);
        });
    }

    @Test
    @DisplayName("결재금액은 수강료와 일치해야 합니다.")
    void paidSession_feeAndAmountHaveToEqual() {
        assertThrows(IllegalArgumentException.class, () -> {
            PaymentPolicy policy = new PaidPaymentPolicy( 1_000_000L, 1);
            policy.validateEnrollment(1L);
        });
    }

    @Test
    @DisplayName("유료 강의는 최대 수강 인원을 초과할 수 없다")
    void paidSession_enrollmentLimitExists() {
        PaidPaymentPolicy paidPaymentPolicy = new PaidPaymentPolicy( 800_000L, 1);
        assertThat(paidPaymentPolicy.canEnroll(2)).isFalse();
    }

    @Test
    @DisplayName("무료 강의는 최대 수강 인원 제한이 없다.")
    void freeSession_enrollmentLimitNotExists() {
        FreePaymentPolicy freePaymentPolicy = new FreePaymentPolicy();
        assertThat(freePaymentPolicy.canEnroll(Integer.MAX_VALUE)).isTrue();
    }
}