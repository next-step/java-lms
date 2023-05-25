package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

public class ChargeTest {
    @Test
    void 생성() {
        new Charge(ChargeStatus.FREE);
        new Charge(ChargeStatus.CHARGE, 500_000);
        assertThatIllegalArgumentException()
                .isThrownBy(() -> new Charge(ChargeStatus.FREE, 100_000));
    }
}
