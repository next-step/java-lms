package nextstep.courses.domain;

import nextstep.courses.domain.session.SessionState;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionStateTest {
    @Test
    @DisplayName("OPEN은 등록이 가능하다")
    void enroll_success() {
        assertThatCode(() -> SessionState.OPEN.validateEnroll())
                .doesNotThrowAnyException();
    }

    @Test
    @DisplayName("READY나 CLOSED는 등록할 수 없다")
    void enroll_fail() {
        assertThatThrownBy(() -> SessionState.READY.validateEnroll())
                .isInstanceOf(IllegalStateException.class);

        assertThatThrownBy(() -> SessionState.CLOSED.validateEnroll())
                .isInstanceOf(IllegalStateException.class);
    }
}