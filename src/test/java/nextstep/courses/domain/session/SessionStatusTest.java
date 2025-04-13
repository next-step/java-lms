package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {
    @Test
    @DisplayName("모집중인 강의인지 확인한다")
    void isRecruiting() {
        // given
        SessionStatus recruiting = SessionStatus.RECRUITING;
        SessionStatus preparing = SessionStatus.PREPARING;
        SessionStatus closed = SessionStatus.CLOSED;

        // when & then
        assertThat(recruiting.isRecruiting()).isTrue();
        assertThat(preparing.isRecruiting()).isFalse();
        assertThat(closed.isRecruiting()).isFalse();
    }
} 