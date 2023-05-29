package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class SessionStateTest {

    @Test
    public void update() throws Exception {
        assertThat(SessionState.PREPARING.updateState()).isEqualTo(SessionState.RECRUITING);
        assertThat(SessionState.RECRUITING.updateState()).isEqualTo(SessionState.CLOSED);
    }

    @Test
    public void update_예외() throws Exception {
        assertThatIllegalArgumentException().isThrownBy(() -> SessionState.CLOSED.updateState());
    }
}
