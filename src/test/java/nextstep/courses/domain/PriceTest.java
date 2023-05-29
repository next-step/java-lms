package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertAll;

public class PriceTest {

    @DisplayName("가격(무료=0/유료!=0) 테스트")
    @Test
    public void 가격_테스트() {
        assertAll(
                () -> assertThat(new Price(0).getType())
                        .isEqualTo(PriceType.FREE),
                () -> assertThat(new Price(2).getType())
                        .isEqualTo(PriceType.PAID)
        );
    }
}
