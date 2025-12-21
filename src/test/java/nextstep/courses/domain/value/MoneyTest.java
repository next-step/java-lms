package nextstep.courses.domain.value;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import org.junit.jupiter.api.Test;

public class MoneyTest {

    @Test
    void 금액이_음수라면_예외가_발생한다() {
        assertThatThrownBy(() -> new Money(-100))
                .isInstanceOf(RuntimeException.class)
                .hasMessageContaining("금액은 0 이상이어야 합니다.");
    }

    @Test
    void 금액이_같다면_같은_Money라고_판단한다() {
        assertThat(new Money(1000)).isEqualTo(new Money(1000));
    }

    @Test
    void 금액이_같지_않다면_다른_Money라고_판단한다() {
        assertThat(new Money(1000)).isNotEqualTo(new Money(500));
    }
}
