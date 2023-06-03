package nextstep.courses.domain.registration;

import nextstep.courses.domain.registration.SessionStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionStatusTest {

    @DisplayName("강의상태 : 정의된 값이 없는경우")
    @ParameterizedTest
    @ValueSource(strings = {"준비, 모집, 시작"})
    void 강의상태_없는경우(String sessionStatus) {
        assertThatIllegalArgumentException()
                .isThrownBy(() -> SessionStatus.findByName(sessionStatus))
                .withMessageMatching("없는 강의상태 입니다.");
    }

    @DisplayName("강의상태 : 준비중")
    @ParameterizedTest
    @ValueSource(strings = {"준비중"})
    void 강의상태_준비중(String sessionStatus) {
        Assertions.assertThat(SessionStatus.findByName(sessionStatus)).isEqualTo(SessionStatus.PREPARING);
    }

    @DisplayName("강의상태 : 모집중")
    @ParameterizedTest
    @ValueSource(strings = {"모집중"})
    void 강의상태_모집중(String sessionStatus) {
        Assertions.assertThat(SessionStatus.findByName(sessionStatus)).isEqualTo(SessionStatus.RECRUITING);
    }

    @DisplayName("강의상태 : 종료")
    @ParameterizedTest
    @ValueSource(strings = {"종료"})
    void 강의상태_종료(String sessionStatus) {
        Assertions.assertThat(SessionStatus.findByName(sessionStatus)).isEqualTo(SessionStatus.CLOSED);
    }
}
