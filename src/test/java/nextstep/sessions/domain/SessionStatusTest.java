package nextstep.sessions.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class SessionStatusTest {

    @ParameterizedTest(name = "강의는 {0} 상태에서 모집 할 수 있다 : {1}")
    @CsvSource(value = {"PREPARING,false", "RECRUITING,true", "CLOSED,false"})
    void canRecruit(SessionStatus status, boolean canRecruit) {
        assertThat(status.canRecruit())
                .isEqualTo(canRecruit);
    }
}
