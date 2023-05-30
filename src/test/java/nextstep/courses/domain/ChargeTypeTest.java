package nextstep.courses.domain;

import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.NullAndEmptySource;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.*;

class ChargeTypeTest {

    @Test
    void testFindChargeType_성공() {
        assertThat(ChargeType.find("CHARGED")).isEqualTo(ChargeType.CHARGED);
        assertThat(ChargeType.find("charged")).isEqualTo(ChargeType.CHARGED);
    }

    @ParameterizedTest
    @NullAndEmptySource
    @ValueSource(strings = {"unknown"})
    void testFindChargeType_실패(String chargeType) {
        assertThatThrownBy(() -> {
            ChargeType.find(chargeType);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 요금 유형 입니다.");
    }
}