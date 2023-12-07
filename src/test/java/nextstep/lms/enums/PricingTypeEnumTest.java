package nextstep.lms.enums;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class PricingTypeEnumTest {
    @DisplayName("무료강의인지 여부 확인")
    @Test
    void 무료강의_확인() {
        Assertions.assertThat(PricingTypeEnum.FREE.isFree()).isTrue();
        Assertions.assertThat(PricingTypeEnum.PAID.isFree()).isFalse();
    }
}