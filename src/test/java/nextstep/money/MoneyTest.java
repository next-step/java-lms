package nextstep.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class MoneyTest {

    @Test
    void create() {
        Money money = Money.wons(1000L);
        assertThat(money).isEqualTo(Money.wons(1000L));
    }

    @DisplayName("현재 금액이 주어진 금액보다 미만인 경우 참/거짓을 반환한다")
    @Test
    void isLessThan() {
        Money money = Money.ZERO;
        assertThat(money.isLessThan(Money.wons(1L))).isTrue();

        money = Money.wons(1000L);
        assertThat(money.isLessThan(Money.ZERO)).isFalse();
    }
}
