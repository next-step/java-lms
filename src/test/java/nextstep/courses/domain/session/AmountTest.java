package nextstep.courses.domain.session;

import nextstep.courses.exception.NotMatchAmountException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class AmountTest {

    @DisplayName("인자로 Payment객체를 전달 받아 결제 금액과 강의 금액이 일치하지 않으면 예외를 발생시킨다.")
    @Test
    void vaidate() {
        // given
        Amount amount = new Amount(12000L);

        // when & then
        assertThatThrownBy(() -> amount.validatePayAmount(10000L)).isInstanceOf(NotMatchAmountException.class)
            .hasMessage("결제 금액이 강의 금액과 일치하지 않습니다. 결제 금액 :: 10,000원");
    }
}
