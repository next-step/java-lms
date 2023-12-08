package nextstep.session.domain;

import static nextstep.session.domain.SessionStatus.RECRUITING;
import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

class SessionStatusTest {

    @ParameterizedTest
    @DisplayName("강의를 들을 수 있는지 물어본다.")
    @CsvSource(value = {"PREPARING, false", "RECRUITING, true", "END, false"})
    void check_taking_lecture(SessionStatus given, boolean expected) {
        // when
        boolean result = given.checkTakingLecture();

        // then
        assertThat(result).isEqualTo(expected);
    }
}
