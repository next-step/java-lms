package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SessionTypeTest {

    @ParameterizedTest
    @CsvSource(value = {"PAID, true", "FREE, false"})
    void 강의_유무료_테스트(SessionType sessionType, boolean result) {
        Assertions.assertThat(sessionType.isPaid()).isEqualTo(result);
    }
}
