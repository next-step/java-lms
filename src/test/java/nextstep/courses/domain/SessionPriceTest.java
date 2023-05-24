package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionPriceTest {
    public static final SessionPrice TDD_SESSION_PRICE = new SessionPrice(800_000);

    @Test
    @DisplayName("강의 금액이 최소 금액(0원) 미만일 경우, IllegalArgumentException 예외 발생")
    void less_than_minimum_session_price_then_throw_IllegalArgumentException() {
        // given
        int invalidSessionPrice = -1_000;

        // when, then
        assertThatThrownBy(() -> new SessionPrice(invalidSessionPrice))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("강의 최소 수강료는 0원입니다: " + invalidSessionPrice);
    }
}