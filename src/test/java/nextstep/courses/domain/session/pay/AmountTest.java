package nextstep.courses.domain.session.pay;

import nextstep.courses.domain.session.pay.Amount;
import nextstep.courses.exception.NotMatchAmountException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AmountTest {

    @DisplayName("인자로 Payment객체를 전달 받아 결제 금액과 강의 금액이 일치하지 않으면 예외를 발생시킨다.")
    @Test
    void vaidate() {
        // given
        Payment payment = new Payment("1", 1L, 1L, 10000L);
        Amount amount = new Amount(12000L);

        // when & then
        assertThatThrownBy(() -> amount.validate(payment)).isInstanceOf(NotMatchAmountException.class)
            .hasMessage("결제 금액이 강의 금액과 일치하지 않습니다. 강의 금액 :: 12,000원");
    }
}
