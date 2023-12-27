package nextstep.session;

import static org.assertj.core.api.Assertions.*;

import nextstep.payments.domain.Payment;
import nextstep.session.domain.SessionPrice;
import nextstep.session.domain.SessionPricing;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionPricingTest {

    @Test
    @DisplayName("무료강의는 누구나 수행가능하다.")
    void canEnrollFreeSession() {
        SessionPrice price = new SessionPrice(0);
        SessionPricing pricing = new SessionPricing(price, 10);

        assertThatCode(() -> pricing.canEnroll(new Payment("payId", 1L, 1L, 0L), 5))
            .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("무료강의는 돈을 지불할 필요가 없다.")
    void freeSessionDoseNotPaid() {
        SessionPrice price = new SessionPrice(0);
        SessionPricing pricing = new SessionPricing(price, 10);

        assertThatThrownBy(() -> pricing.canEnroll(new Payment("payId", 1L, 1L, 100L), 5))
            .isInstanceOf(IllegalArgumentException.class);
    }

    @Test
    @DisplayName("유료강의는 수강금액이 일치하지 않으면 등록이 불가능 하다.")
    void canEnrollPaidSessionPrice() {
        SessionPrice paidPrice = new SessionPrice(100);
        SessionPricing pricing = new SessionPricing(paidPrice, 10);

        assertThatThrownBy(() -> pricing.canEnroll(new Payment("payId", 1L, 1L, 99L), 9))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("결제 금액이 수강료와 일치하지 않습니다.");

    }

    @Test
    @DisplayName("유료강의는 남은 자리가 없다면 등록이 불가능하다.")
    void canEnrollPaidSessionParticipants() {
        SessionPrice paidPrice = new SessionPrice(100);
        SessionPricing pricing = new SessionPricing(paidPrice, 10);

        assertThatThrownBy(() -> pricing.canEnroll(new Payment("payId", 1L, 1L, 100L), 10))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("최대 수강 인원을 초과했습니다.");
    }

}

