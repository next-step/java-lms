package nextstep.payments.domain;

import static nextstep.payments.domain.Money.MINIMUM_MONEY;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class MoneyTest {

    @Test
    @DisplayName("새로운 금액을 생성한다.")
    void Money() {
        assertThat(new Money(MINIMUM_MONEY))
                .isEqualTo(new Money(MINIMUM_MONEY));
    }

    @Test
    @DisplayName("금액이 0원 미만이면 예외를 던진다.")
    void NegativeMoneyAmount_Exception() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Money(MINIMUM_MONEY - 1));
    }
}
