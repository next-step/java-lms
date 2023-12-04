package nextstep.courses.domain;

import nextstep.courses.exception.SessionPriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.payments.domain.PaymentTest.paymentOneThousand;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class SessionPriceTest {

    public static SessionPrice sessionPriceTenThousand() {
        return new SessionPrice(10000);
    }

    public static SessionPrice sessionPriceOneThousand() {
        return new SessionPrice(1000);
    }

    @Test
    @DisplayName("실패 - 강의 금액과 결제한 금액이 같지 않을 경우 예외가 발생한다.")
    void fail_session_price_not_equal_payment_price() {
        SessionPrice sessionPrice = new SessionPrice(999);
        assertThatThrownBy(() -> sessionPrice.isSameBy(paymentOneThousand()))
                .isInstanceOf(SessionPriceException.class)
                .hasMessage("결제 금액과 강의 금액이 일치 하지 않습니다.");
    }

}
