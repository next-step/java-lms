package nextstep.courses.domain;

import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {

    @ParameterizedTest(name = "{0} 때는 신청이 {1} 하다")
    @CsvSource({"PREPARING, false", "RECRUITING, true", "END, false"})
    void canApply(SessionStatus input,
                  boolean expected) {
        boolean actual = input.canApply();

        assertThat(actual).isEqualTo(expected);
    }

}
