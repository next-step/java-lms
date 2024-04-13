package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionStatusTest {

    @Test
    @DisplayName("강의 상태가 모집중 상태인지 확인한다.")
    void isRecruiting() {
        assertTrue(SessionStatus.RECRUITING.isRecruiting());
        assertFalse(SessionStatus.READY.isRecruiting());
    }

}
