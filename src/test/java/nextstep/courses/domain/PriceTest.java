package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class PriceTest {

    @DisplayName("강의 유료 금액 생성")
    @Test
    void 강의유료금액생성() {
        // given
        boolean isFree = false;
        int money = 10000;
        // when
        Price price = new Price(isFree, money);
        // then
        assertThat(price.isFree()).isFalse();
    }

    @DisplayName("강의 무료 생성")
    @Test
    void 강의무료생성() {
        // given
        boolean isFree = true;
        int money = 10000;
        // when
        Price price = new Price(isFree, money);
        // then
        assertThat(price.isFree()).isTrue();
    }
}
