package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @DisplayName("금액이 음수일 때 예외를 던진다")
    @Test
    void negativePriceException() {
        assertThatThrownBy(() -> new Price(-5000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("금액은 음수일 수 없습니다");
    }

    @DisplayName("납부한 금액이 수강료와 같지 않으면 예외를 던진다")
    @Test
    void wrongPay() {
        Price price = new Price(30000);
        assertThatThrownBy(() -> price.validatePrice(30001))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("정확한 수강료를 납부해주세요");
    }

    @DisplayName("정확한 수강료가 납부되면 true를 반환한다")
    @Test
    void correctpay() {
        Price price = new Price(30000);
        assertThat(price.validatePrice(30000));
    }
}