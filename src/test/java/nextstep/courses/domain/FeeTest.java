package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class FeeTest {

    @Test
    @DisplayName("무료강의일 경우, 수강료가 없고 유료강의일 경우, 수강료가 있어야 한다.")
    void create_fee_test() {
        assertThrows(IllegalArgumentException.class, () -> new Fee(1L, SessionType.FREE));
        assertThrows(IllegalArgumentException.class, () -> new Fee(0L, SessionType.PAID));
        assertDoesNotThrow(() -> new Fee(1L, SessionType.PAID));
        assertDoesNotThrow(() -> new Fee(0L, SessionType.FREE));
    }

    @Test
    @DisplayName("결제금액과 수강료가 같지 않으면 에러가 발생한다.")
    void enroll_fee_test() {
        Fee fee = new Fee(1L, SessionType.PAID);
        assertThrows(IllegalArgumentException.class, () -> fee.enroll(1L, 1L));
    }
}
