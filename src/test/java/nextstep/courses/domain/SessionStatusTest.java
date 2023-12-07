package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {
    @Test
    @DisplayName("isRecruiting_SessionStatus_RECRUITING인지 비교")
    void isRecruiting() {
        assertThat(SessionStatus.PREPARING.isRecruiting()).isFalse();
        assertThat(SessionStatus.RECRUITING.isRecruiting()).isTrue();
        assertThat(SessionStatus.CLOSED.isRecruiting()).isFalse();
    }
}
