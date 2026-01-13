package nextstep.courses.domain.policy;

import nextstep.courses.domain.money.Money;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class FreeSessionPolicyTest  {
    @Test
    public void passPolicy() {
        assertThatCode(() -> new FreeSessionPolicy().validate(Money.FREE)).doesNotThrowAnyException();
    }

    @Test
    public void violatePolicy() {
        assertThatThrownBy(() -> new FreeSessionPolicy().validate(new Money(1000))).isInstanceOf(IllegalArgumentException.class);
    }
}
