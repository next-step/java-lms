package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

class SessionStatusTest {
    @Test
    void 강의_상태는_모집중일때만_true_반환() {
        assertAll(
                () -> assertThat(SessionStatus.READY.canEnrollment()).isFalse(),
                () -> assertThat(SessionStatus.OPEN.canEnrollment()).isTrue(),
                () -> assertThat(SessionStatus.CLOSED.canEnrollment()).isFalse()
        );
    }
}