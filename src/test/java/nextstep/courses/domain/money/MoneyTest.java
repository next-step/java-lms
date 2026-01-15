package nextstep.courses.domain.money;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class MoneyTest {
    @Test
    public void create() {
        assertThat(new Money("1000")).isEqualTo(new Money(1000));
    }


    @Test
    public void equal() {
        assertThat(new Money("1000").isEqualTo(new Money(1000))).isTrue();
    }

    @Test
    public void notEqual() {
        assertThat(new Money("1000").isEqualTo(new Money(1))).isFalse();
    }

    @Test
    public void free() {
        assertThat(Money.FREE).isEqualTo(new Money(0));
    }
}
