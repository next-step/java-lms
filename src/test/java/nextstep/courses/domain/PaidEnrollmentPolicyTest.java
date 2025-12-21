package nextstep.courses.domain;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class PaidEnrollmentPolicyTest {

    @Test
    @DisplayName("유료강의 - 성공")
    void validateEnrollment_success() {
        Money price = new Money(5000);
        PaidEnrollmentPolicy policy = new PaidEnrollmentPolicy(price);

        Payment payment = new Payment("p1", 1L, 1L, 5_000L);

        assertThatCode(() -> policy.validateEnrollment(payment))
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유료강의 - 금액 불일치 실패")
    void validateEnrollment_fail_amount() {
        Money price = new Money(5000);
        PaidEnrollmentPolicy policy = new PaidEnrollmentPolicy(price);

        Payment payment = new Payment("p1", 1L, 1L, 4_000L);

        assertThatThrownBy(() -> policy.validateEnrollment(payment))
                .isInstanceOf(IllegalArgumentException.class);
    }
}