package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.Arguments;
import org.junit.jupiter.params.provider.MethodSource;

import java.util.stream.Stream;

import static nextstep.courses.domain.SessionChargeType.*;
import static org.assertj.core.api.Assertions.*;

class SessionPriceTest {
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

    @ParameterizedTest(name = "[{index}/2] {displayName}")
    @MethodSource("priceAndExpectedChargeType")
    @DisplayName("강의 수강료에 따른 강의 비용 타입")
    void session_charge_type_according_to_session_price(int price, SessionChargeType expectedType) {
        // given
        SessionPrice sessionPrice = new SessionPrice(price);

        // when, then
        assertThat(sessionPrice.type()).isEqualTo(expectedType);
    }

    static Stream<Arguments> priceAndExpectedChargeType() {
        return Stream.of(
                Arguments.arguments(0, FREE),
                Arguments.arguments(800_000, CHARGED)
        );
    }
}