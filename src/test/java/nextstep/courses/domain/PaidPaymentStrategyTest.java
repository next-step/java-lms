package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

@DisplayName("유료 강의 테스트")
class PaidPaymentStrategyTest {

    @DisplayName("유료 강의는 결제 금액을 가진다")
    @Test
    void paidSessionPrice() {
        PaidPaymentStrategy paidPaymentStrategy = new PaidPaymentStrategy(800000);
        assertThat(paidPaymentStrategy.chargeValue()).isGreaterThan(0);
        assertThat(paidPaymentStrategy.chargeValue()).isEqualTo(800000);
    }
}
