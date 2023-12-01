package nextstep.sessions.domain.data.type;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class SessionStateTest {

    @Test
    void isPay() {
        assertThat(SessionState.RECRUITING.isRecruiting()).isTrue();
    }
}
