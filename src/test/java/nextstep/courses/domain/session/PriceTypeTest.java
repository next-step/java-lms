package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PriceTypeTest {
    @Test
    @DisplayName("금액에 따른 PriceType 테스트")
    void checkPriceType() {
        assertAll(
                () -> assertThat(PriceType.checkPriceType(0))
                        .isEqualTo(PriceType.FREE),
                () -> assertThat(PriceType.checkPriceType(1))
                        .isEqualTo(PriceType.PAID)
        );
    }
}
