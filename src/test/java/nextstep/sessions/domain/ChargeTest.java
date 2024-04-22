package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;
import static org.assertj.core.api.AssertionsForClassTypes.assertThatExceptionOfType;

public class ChargeTest {

    @DisplayName("무료 강의의 가격은 0이어야 합니다")
    @Test
    void freeSessionChargeEqualsZero() {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Charge(ChargeStatus.FREE, 100_000));

        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> {
                    new Charge(ChargeStatus.FREE, 100_000);
                }).withMessageMatching("무료 강의의 가격은 0이어야 합니다.");
    }
}
