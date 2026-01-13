package nextstep.courses.domain.policy;

import nextstep.courses.domain.capacity.Capacity;
import nextstep.courses.domain.money.Money;
import org.junit.jupiter.api.Test;
import static org.assertj.core.api.Assertions.*;

public class PaidSessionPolicyTest {
    @Test
    void wrongPaidAmount() {
        assertThatThrownBy(() -> new PaidSessionPolicy(1000, 1000).validate(new Money(1), new Capacity(500))).isInstanceOf(IllegalArgumentException.class);
    }
}
