package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionTypeTest {

    @Test
    @DisplayName("SessionType이 존재하지 않으면 에러가 발생한다")
    void not_exist_exception_test() {
        assertThrows(IllegalArgumentException.class, () -> SessionType.of("NOT_EXIST"));
    }
}
