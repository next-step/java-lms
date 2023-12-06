package nextstep.sessions.domain.data.type;

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
