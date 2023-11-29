package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class PriceTest {

    @Test
    @DisplayName("가격은 0원 이상이어야 한다.")
    void priceShouldBePositive() {
        Assertions.assertThatIllegalArgumentException().isThrownBy(() -> {
            new Price(-1);
        });
    }

}
