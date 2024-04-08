package nextstep.sessions.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static nextstep.sessions.domain.SessionType.FREE;
import static nextstep.sessions.domain.SessionType.PAID;

public class SessionTypeTest {

    @Test
    void PAY() {
        Assertions.assertThat(FREE == FREE).isTrue();
    }

    @Test
    void FREE() {
        Assertions.assertThat(FREE == FREE).isTrue();
    }

    @DisplayName("강의타입이 주어진 가격에 따라 무료인지 유료인지 결정한다")
    @ParameterizedTest
    @CsvSource({"50000, PAID", "0, FREE"})
    void determineSessionType(int price, SessionType type) {
        Assertions.assertThat(SessionType.determineSessionType(price)).isEqualTo(type);
    }

}
