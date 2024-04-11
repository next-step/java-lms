package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class StudentsTest {

    @Test
    @DisplayName("무료강의일 경우, 수강인원 제한이 없고, 유료강의일 경우, 수강료가 있어야 한다.")
    void create_Students_test() {
        assertThrows(IllegalArgumentException.class, () -> new Students(1, SessionType.FREE));
        assertThrows(IllegalArgumentException.class, () -> new Students(0, SessionType.PAID));
        assertDoesNotThrow(() -> new Students(1, SessionType.PAID));
        assertDoesNotThrow(() -> new Students(null, SessionType.FREE));
    }

}
