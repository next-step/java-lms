package nextstep.sessions.domain.data.type;

import nextstep.sessions.domain.data.session.SessionRunningState;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionRunningStateTest {

    @Test
    void name() {
        assertThat(SessionRunningState.PREPARING.isRunning()).isFalse();
        assertThat(SessionRunningState.DONE.isRunning()).isFalse();
        assertThat(SessionRunningState.RUNNING.isRunning()).isTrue();
    }
}
