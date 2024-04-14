package nextstep.sessions.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class PriceTest {

    @DisplayName("price객체를 생성한다")
    @Test
    void price() {
        Assertions.assertThat(new Price(1000L)).isEqualTo(new Price(1000L));
    }

    @DisplayName("같은 가격이 아니면 true를 반환한다")
    @Test
    void isNotSamePrice() {
        Assertions.assertThat(new Price(1000L).isNotSamePrice(2000L)).isTrue();
        Assertions.assertThat(new Price(1000L).isNotSamePrice(1000L)).isFalse();
    }

}
