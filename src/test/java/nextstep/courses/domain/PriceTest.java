package nextstep.courses.domain;

import nextstep.courses.domain.type.Price;
import nextstep.courses.exception.NegativePriceException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class PriceTest {

    @Test
    @DisplayName("금액이 음수일 경우 에러 발생한다")
    public void negative_price() {
        assertThatExceptionOfType(NegativePriceException.class)
            .isThrownBy(() -> new Price(BigDecimal.valueOf(-1)))
            .withMessageMatching("금액은 음수일 수 없습니다.");
    }

    @Test
    @DisplayName("금액이 같은지 비교할 수 있다")
    public void equal_price() {
        Price price = new Price(BigDecimal.valueOf(1_000));
        assertThat(price.isEqualAmount(BigDecimal.valueOf(1_000))).isTrue();
        assertThat(price.isEqualAmount(BigDecimal.valueOf(2_000))).isFalse();
    }

}
