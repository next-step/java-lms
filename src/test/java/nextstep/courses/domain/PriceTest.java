package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PriceTest {
    @Test
    void checkPriceType() {
        assertAll(
                () -> assertThat(new Price(0).checkPriceType())
                        .isEqualTo(PriceType.FREE),
                () -> assertThat(new Price(2).checkPriceType())
                        .isEqualTo(PriceType.PAID)
        );
    }
}
