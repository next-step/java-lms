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
        assertThat(SessionState.of("모집중")).isEqualTo(SessionState.RECRUITING);
        assertThat(SessionState.of("준비중")).isEqualTo(SessionState.PREPARING);
        assertThat(SessionState.of("종료")).isEqualTo(SessionState.END);
    }
}
