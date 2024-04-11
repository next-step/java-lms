package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class SessionStatusTest {

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능")
    void is_recruiting() {
        assertTrue(SessionStatus.RECRUITING.isRecruiting());
        assertFalse(SessionStatus.CLOSED.isRecruiting());
        assertFalse(SessionStatus.PREPARING.isRecruiting());
    }
}
