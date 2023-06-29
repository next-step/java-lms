package nextstep.courses.domain.registration;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {
    @DisplayName("강의상태 : 정의된 값이 없는경우")
    @ParameterizedTest
    @ValueSource(strings = {"준비, 모집, 시작"})
    void 강의상태_없는경우(String sessionStatus) {
        assertThat(SessionStatus.findByName(sessionStatus)).isEqualTo(SessionStatus.valueOf(SessionStatus.NONE.toString()));
    }

    @DisplayName("강의상태 : 준비중")
    @ParameterizedTest
    @ValueSource(strings = {"준비중"})
    void 강의상태_준비중(String sessionStatus) {
        assertThat(SessionStatus.findByName(sessionStatus)).isEqualTo(SessionStatus.PREPARING);
    }

    @DisplayName("강의상태 : 종료")
    @ParameterizedTest
    @ValueSource(strings = {"종료"})
    void 강의상태_종료(String sessionStatus) {
        assertThat(SessionStatus.findByName(sessionStatus)).isEqualTo(SessionStatus.CLOSED);
    }

    @DisplayName("강의상태 : 진행중")
    @ParameterizedTest
    @ValueSource(strings = {"진행중"})
    void 강의상태_진행중(String sessionStatus) {
        assertThat(SessionStatus.findByName(sessionStatus)).isEqualTo(SessionStatus.PROGRESSING);
    }
}
