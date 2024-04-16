package nextstep.courses.domain.session.strategy;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import nextstep.courses.domain.session.EnrollmentCount;
import nextstep.payments.domain.Money;

class PaidSessionStrategyTest {

    private final PaidSessionStrategy paidSessionStrategy = new PaidSessionStrategy(
            new Money(10000),
            new EnrollmentCount(10)
    );

    @Test
    @DisplayName("수강료가 최소 수강료 미만인 경우 예외를 던진다.")
    void FeeIsLessThanMinimum_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaidSessionStrategy(new Money(0), new EnrollmentCount(1)));
    }

    @Test
    @DisplayName("수강 인원 제한이 최소 인원 제한 미만인 경우 예외를 던진다.")
    void EnrollmentCountIsLessThanMinimum_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new PaidSessionStrategy(new Money(10000), new EnrollmentCount(0)));
    }

    @Test
    @DisplayName("수강 인원 제한을 초과하지 않고, 결제 금액과 수강료가 일치하는 경우 true를 반환한다.")
    void CanEnroll_True() {
        assertThat(paidSessionStrategy.isPaymentSufficient(new Money(10000)))
                .isTrue();
        assertThat(paidSessionStrategy.canEnrollMoreStudents(new EnrollmentCount(1)))
                .isTrue();
    }

    @Test
    @DisplayName("수강 인원 제한을 초과하는 경우 false를 반환한다.")
    void CanEnroll_ExceedMaxEnrollmentCount_False() {
        assertThat(paidSessionStrategy.canEnrollMoreStudents(new EnrollmentCount(11)))
                .isFalse();
    }

    @Test
    @DisplayName("결제 금액과 수강료가 일치하지 않는 경우 false를 반환한다.")
    void CanEnroll_NotEqualFeeAndPayment_False() {
        assertThat(paidSessionStrategy.isPaymentSufficient(new Money(9000)))
                .isFalse();
    }
}
