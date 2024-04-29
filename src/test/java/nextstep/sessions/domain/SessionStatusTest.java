package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.sessions.domain.SessionStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

public class SessionStatusTest {
    @DisplayName("강의 상태가 IN_PROGRESS인지 아닌지 확인한다")
    @Test
    void isNotInProgress() {
        assertThat(PREPARING.isNotInProgress()).isTrue();
        assertThat(END.isNotInProgress()).isTrue();

        assertThat(IN_PROGRESS.isNotInProgress()).isFalse();
    }
}
