package nextstep.courses.strategy;

import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PaymentStrategyTest {

    @Test
    @DisplayName("무료인 경기는 금액을 내지 않아도 된다.")
    public void free(){
        FreePaymentStrategy freePaymentStrategy = new FreePaymentStrategy();
        assertTrue(freePaymentStrategy.payable(new Payment()));
    }

    @Test
    @DisplayName("유료인 경기는 낸 가격과 동일한 가격이다.")
    public void paid() {
        PaidPaymentStrategy paidPaymentStrategy = new PaidPaymentStrategy(1000L);
        assertTrue(paidPaymentStrategy.payable(new Payment("1", 1L, 1L, 1000L)));
    }
}