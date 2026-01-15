package nextstep.courses.domain.policy;

import nextstep.courses.domain.capacity.Capacity;
import nextstep.courses.domain.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class FreeSessionPolicyTest {
    @Test
    public void passPolicy() {
        assertThatCode(() -> new FreeSessionPolicy().validate(Money.FREE, new Capacity(100))).doesNotThrowAnyException();
    }

    @Test
    public void violatePolicy() {
        assertThatThrownBy(() -> new FreeSessionPolicy().validate(new Money(1000), new Capacity(100))).isInstanceOf(IllegalArgumentException.class);
    }
}
