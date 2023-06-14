package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionProgressTest {

    @Test
    @DisplayName(value = "PROGRESS 상태일 경우 true 반환 검사")
    void test1() {
        assertTrue(SessionProgressStatus.PREPARING.isRecruitable());
        assertTrue(SessionProgressStatus.PROGRESS.isRecruitable());
        assertFalse(SessionProgressStatus.CLOSE.isRecruitable());
    }

}
