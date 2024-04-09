package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class SessionPriceTest {

    @Test
    void 입력받은_금액과_다르면_예외가_발생한다() {
        final SessionPrice sessionPrice = new SessionPrice(10000L);

        assertThatIllegalArgumentException().isThrownBy(() -> sessionPrice.validatePriceEquality(20000L))
                .withMessage("현재 가격은 20000원 입니다. 10000원과 일치하지 않습니다.");
    }

    @ParameterizedTest
    @ValueSource(longs = { -1, -1000 })
    void 가격이_음수이면_예외가_발생한다(final long price) {
        assertThatIllegalArgumentException().isThrownBy(() -> new SessionPrice(price))
                .withMessage("입력한 가격은 " + price + "원 입니다. 가격은 0보다 작을 수 없습니다.");
    }

    @Test
    void 유료인_경우_true를_반환한다() {
        // given
        final SessionPrice sessionPrice = new SessionPrice(1000L);

        // when
        final boolean free = sessionPrice.isNotFree();

        // then
        assertThat(free).isTrue();
    }

    @Test
    void 무료인_경우_false를_반환한다() {
        // given
        final SessionPrice sessionPrice = new SessionPrice(0L);

        // when
        final boolean notFree = sessionPrice.isNotFree();

        // then
        assertThat(notFree).isFalse();
    }
}
