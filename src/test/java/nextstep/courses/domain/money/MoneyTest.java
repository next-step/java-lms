package nextstep.courses.domain.money;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class MoneyTest {
    @Test
    public void create() {
        assertThat(new Money("1000")).isEqualTo(new Money(1000));
    }
}
