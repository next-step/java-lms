package nextstep.courses.domain.session.strategy;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PaidSessionStrategyTest {

    private static PaidSessionStrategy paidSessionStrategy;

    @BeforeEach
    void init() {
        paidSessionStrategy = new PaidSessionStrategy(
                new Money(10000),
                new EnrollmentCount(10)
        );
    }

    @Test
    @DisplayName("수강 인원 제한을 초과하지 않고, 결제 금액과 수강료가 일치하는 경우 true를 반환한다.")
    void CanEnroll_True() {
        assertThat(paidSessionStrategy.canEnroll(new Money(10000), new EnrollmentCount(1)))
                .isTrue();
    }

    @Test
    @DisplayName("수강 인원 제한을 초과하는 경우 false를 반환한다.")
    void CanEnroll_ExceedMaxEnrollmentCount_False() {
        assertThat(paidSessionStrategy.canEnroll(new Money(10000), new EnrollmentCount(11)))
                .isFalse();
    }

    @Test
    @DisplayName("결제 금액과 수강료가 일치하지 않는 경우 false를 반환한다.")
    void CanEnroll_NotEqualFeeAndPayment_False() {
        assertThat(paidSessionStrategy.canEnroll(new Money(9999), new EnrollmentCount(1)))
                .isFalse();
    }
}
