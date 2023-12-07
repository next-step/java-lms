package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

public class SessionStateTest {
    @DisplayName("강의 상태가 맞지 않으면 에러")
    @Test
    void 강의_상태_에러() {
        assertThatExceptionOfType(IllegalArgumentException.class)
                .isThrownBy(() -> SessionState.matchState("error"))
                .withMessage("강의 상태가 올바르지 않습니다.");
    }
}
