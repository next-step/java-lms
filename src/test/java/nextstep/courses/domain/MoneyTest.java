package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class MoneyTest {

    @Test
    @DisplayName("금액이 같으면, true")
    void isEqualTo() {
        Money money = new Money(5000);
        assertThat(money.isEqualTo(new Money(5000))).isTrue();
    }
}