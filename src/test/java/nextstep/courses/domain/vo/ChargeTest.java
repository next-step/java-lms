package nextstep.courses.domain.vo;

import nextstep.courses.code.ChargeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChargeTest {

    public static Charge CHARGE_FREE = new Charge(ChargeType.FREE, 0L);
    public static Charge CHARGE_PAID_100K = new Charge(ChargeType.PAID, 100_000L);

    @Test
    @DisplayName("수강 금액 실패 테스트 - 금액 있는 무료 강의")
    void chargeFreeFailTest() {
        assertThatThrownBy(() -> {
            new Charge(ChargeType.FREE, 100_00L);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("무료 강의는 가격이 0이어야 합니다");

    }

    @Test
    @DisplayName("수강 금액 실패 테스트 - 0원 유료 강의")
    void chargePaidFailTest() {
        assertThatThrownBy(() -> {
            new Charge(ChargeType.PAID, 0L);
        }).isInstanceOf(IllegalArgumentException.class)
                .hasMessage("유료 강의는 가격이 0보다 커야 합니다");

    }
}
