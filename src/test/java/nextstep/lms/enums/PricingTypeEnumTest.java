package nextstep.lms.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PricingTypeEnumTest {
    @DisplayName("유료강의인지 확인")
    @Test
    void 유료강의_확인() {
        Assertions.assertThat(PricingTypeEnum.PAID.isPaid()).isTrue();
        Assertions.assertThat(PricingTypeEnum.FREE.isPaid()).isFalse();
    }
}