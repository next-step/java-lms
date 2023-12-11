package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class ChargeTest {
    @Test
    @DisplayName("무료 강의에 가격이 있을 때 예외가 발생한다.")
    void create_exception() {
        Assertions.assertThatIllegalArgumentException()
                .isThrownBy(() -> new Charge(ChargeStatus.FREE, 100000));
    }

    @Test
    @DisplayName("강의 생성 할 수 있다 ")
    void create() {
        Charge charge = new Charge(ChargeStatus.CHARGE, 100000);
        Assertions.assertThat(charge).isEqualTo(new Charge(ChargeStatus.CHARGE, 100000));
    }
}
