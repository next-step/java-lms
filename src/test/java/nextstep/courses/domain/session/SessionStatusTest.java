package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

class SessionStatusTest {

    @Test
    @DisplayName(value = "RECRUITING 상태일 경우 true 반환 검사")
    void test1() {
        assertTrue(SessionStatus.RECRUITING.isRecruitable());
        assertFalse(SessionStatus.PREPARING.isRecruitable());
        assertFalse(SessionStatus.CLOSE.isRecruitable());
    }

}
