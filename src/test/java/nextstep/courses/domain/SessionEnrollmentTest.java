package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionEnrollmentTest {

    @Test
    @DisplayName("조건에 맞지 않게 강의를 개설하면 에러가 난다.")
    void createSessionEnrollment() {
        assertThrows(IllegalArgumentException.class, () -> new SessionEnrollment(SessionType.PAID, 1L, 0));
        assertThrows(IllegalArgumentException.class, () -> new SessionEnrollment(SessionType.PAID, 0L, 1));
        assertThrows(IllegalArgumentException.class, () -> new SessionEnrollment(SessionType.FREE, 1L, 0));
        assertThrows(IllegalArgumentException.class, () -> new SessionEnrollment(SessionType.FREE, 0L, 1));
    }
}
