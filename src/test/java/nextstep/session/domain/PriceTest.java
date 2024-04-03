package nextstep.session.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PriceTest {

    @DisplayName("비용을 완전히 지불하면 isFullyPaid는 true를 반환한다.")
    @Test
    void FullyPaidForFullyPaid() {
        // given
        Price price = new Price(10000);

        // then
        Assertions.assertThat(price.isFullyPaid(10000)).isTrue();
    }

    @DisplayName("비용을 덜 지불하면 isFullyPaid는 false를 반환한다.")
    @Test
    void FullyPaidForNotFullyPaid() {
        // given
        Price price = new Price(10000);

        // then
        Assertions.assertThat(price.isFullyPaid(5000)).isFalse();
    }
}
