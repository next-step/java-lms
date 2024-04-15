package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.ChargeStatus.PRICE_OF_FREE_SESSION_ERROR_MESSAGE;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class ChargeTest {
    @DisplayName("강의 가격 생성 기능 테스트")
    @Test
    void create_charger_test(){
        new Charge(ChargeStatus.FREE);
        new Charge(ChargeStatus.PAID, 800_000);
    }

    @DisplayName("실패 - 무료 강의에 가격 입력 테스트")
    @Test
    void create_valid_charger_test(){
        assertThatThrownBy(() -> {
            new Charge(ChargeStatus.FREE, 100_000);
        }).hasMessageContaining(PRICE_OF_FREE_SESSION_ERROR_MESSAGE);
    }

}