package nextstep.courses.domain;

import nextstep.courses.exception.IncorrectAmountException;
import nextstep.courses.exception.NotPositiveException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

class AmountTest {

    @Test
    @DisplayName("금액이 음수면 예외 처리 한다")
    void newAmount() {
        assertThrows(NotPositiveException.class, () -> new Amount(-1), "금액은 양수만 가능합니다.");
    }

    @Test
    @DisplayName("금액이 같은지 확인 가능하다")
    void isSameAmount() {
        Amount amount = new Amount(20000L);
        Payment payment = new Payment("테스트", 0L, 0L, 20000L);

        assertDoesNotThrow(() -> amount.validateAmount(20000L));
    }

    @Test
    @DisplayName("금액이 다른지 확인 가능하다")
    void isSameAmount2() {
        Amount amount = new Amount(20000L);
        Payment payment = new Payment("테스트", 0L, 0L, 10000L);

        assertThrows(IncorrectAmountException.class, () -> amount.validateAmount(10000L), "결제 금액과 강의 금액이 다릅니다.");
    }
}
