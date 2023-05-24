package nextstep.courses.domain;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

class SessionTest {
    private Session preparingSession;
    private Session recruitingSession;

    @BeforeEach
    void setUp() {
        preparingSession = new Session(1,"session1",SessionStatusType.PREPARING,10);
        recruitingSession = new Session(2,"session2",SessionStatusType.RECRUITING,10);
    }

    @Test
    void register_성공() {
        assertThat(recruitingSession.register()).isEqualTo(1);
    }

    @Test
    void checkExceedMaximumNumberOfStudents_최대_수강_인원_초과() {
        assertThatThrownBy(() -> {
            for (int i = 0; i < 11; i++) {
                recruitingSession.register();
            }
        }).isInstanceOf(RuntimeException.class);
    }

    @Test
    void checkRecruiting_모집중_아님() {
        assertThatThrownBy(() -> {
            preparingSession.register();
        }).isInstanceOf(RuntimeException.class);
    }
}