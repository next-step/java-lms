package nextstep.courses.domain.vo;

import nextstep.courses.code.ChargeType;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class ChargeTest {

    public static Charge CHARGE_FREE = new Charge(0L);
    public static Charge CHARGE_PAID_100K = new Charge(100_000L);

    @Test
    @DisplayName("무료 강의 생성 테스트")
    void createFreeChargeTest() {
        Charge charge = new Charge(0L);
        assertThat(charge.type()).isEqualTo(ChargeType.FREE);
        assertThat(charge.price()).isZero();
    }

    @Test
    @DisplayName("유료 강의 생성 테스트")
    void createPaidChargeTest() {
        Charge charge = new Charge(100_000L);
        assertThat(charge.type()).isEqualTo(ChargeType.PAID);
        assertThat(charge.price()).isEqualTo(100_000L);
    }

    @Test
    @DisplayName("금액 타입 실패 테스트 - 음수 금액")
    void createChargeFailForNegativeNumberTest() {
        assertThatThrownBy(() -> new Charge(-1L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("가격은 음수일 수 없습니다.");
    }
}
