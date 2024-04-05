package nextstep.money;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class MoneyTest {

    @Test
    void create() {
        Money money = Money.wons(1000L);
        assertThat(money).isEqualTo(Money.wons(1000L));
    }

    @Test
    void 음수는_허용하지_않습니다() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> Money.wons(-1))
                .withMessage("음수는 허용하지 않습니다");
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
