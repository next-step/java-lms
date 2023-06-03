package nextstep.courses.domain;

import nextstep.courses.domain.payment.FreePaymentStrategy;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

@DisplayName("무료 강의 테스트")
class FreePaymentStrategyTest {

    @DisplayName("무료 강의는 결제 금액이 0원이다")
    @Test
    void freePaymentPrice() {
        FreePaymentStrategy freePaymentStrategy = new FreePaymentStrategy();
        Assertions.assertThat(freePaymentStrategy.chargeValue()).isEqualTo(0);
    }
}
