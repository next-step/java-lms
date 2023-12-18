package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionStateTest {
    @Test
    @DisplayName("강의 상태 준비중, 모집중, 종료가 아니면 예외가 던져진다")
    void state_exception() {
        assertThatIllegalArgumentException().isThrownBy(() -> {
            SessionState.of("수강중");
        });
    }

    @Test
    @DisplayName("강의 상태 준비중, 모집중, 종료 3가지")
    void create() {
        SessionState sessionState1 = SessionState.of("모집중");
        SessionState sessionState2 = SessionState.of("준비중");
        SessionState sessionState3 = SessionState.of("종료");
        assertThat(sessionState1).isEqualTo(SessionState.RECRUITING);
        assertThat(sessionState2).isEqualTo(SessionState.PREPARING);
        assertThat(sessionState3).isEqualTo(SessionState.END);
    }
}
