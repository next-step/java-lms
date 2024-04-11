package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeeTest {

    @Test
    @DisplayName("무료강의일 경우, 수강료가 없고 유료강의일 경우, 수강료가 있어야 한다.")
    void create_fee_test() {
        assertThrows(IllegalArgumentException.class, () -> new Fee(1, SessionType.FREE));
        assertThrows(IllegalArgumentException.class, () -> new Fee(0, SessionType.PAID));
        assertDoesNotThrow(() -> new Fee(1, SessionType.PAID));
        assertDoesNotThrow(() -> new Fee(0, SessionType.FREE));

    }

}
