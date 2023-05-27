package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

public class SessionStatusTest {

    @ParameterizedTest
    @CsvSource(value = {"RECRUIT, true", "READY, false", "QUIT, false"})
    void 수강신청_가능여부_테스트(SessionStatus sessionStatus, boolean result) {
        Assertions.assertThat(sessionStatus.isRecruit()).isEqualTo(result);
    }

}
