package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionTypeTest {

    @DisplayName("강의타입이 주어진 가격에 따라 무료인지 유료인지 결정한다")
    @ParameterizedTest
    @CsvSource({"50000, PAID", "0, FREE"})
    void determineSessionType(int price, SessionType type) {
        assertThat(SessionType.determineSessionType(price)).isEqualTo(type);
    }

    @DisplayName("무료강의는 수강신청의 제한이 없다")
    @Test
    void FreeSessionIsAlwaysTrue() {
        assertThat(SessionType.FREE.canEnroll(10, 0)).isTrue();
    }
}
