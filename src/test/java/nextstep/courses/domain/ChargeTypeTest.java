package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class ChargeTypeTest {

    @Test
    void testFindChargeType_성공() {
        assertThat(ChargeType.find("CHARGED")).isEqualTo(ChargeType.CHARGED);
        assertThat(ChargeType.find("charged")).isEqualTo(ChargeType.CHARGED);
    }

    @Test
    void testFindChargeType_실패() {
        assertThatThrownBy(() -> {
            ChargeType.find("unknown");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 요금 유형 입니다.");

        assertThatThrownBy(() -> {
            ChargeType.find("");
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 요금 유형 입니다.");

        assertThatThrownBy(() -> {
            ChargeType.find(null);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining("유효하지 않은 요금 유형 입니다.");
    }
}