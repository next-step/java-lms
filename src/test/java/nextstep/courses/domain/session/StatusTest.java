package nextstep.courses.domain.session;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.session.Status.*;
import static org.junit.jupiter.api.Assertions.*;

public class StatusTest {

    @DisplayName("인자로 강의 상태를 전달 받아 모집중 상태이면 true를 반환 하고 아니면 false를 반환한다.")
    @Test
    void validateStatus() {
        assertAll(
            () -> assertFalse(isNotRecruiting(RECRUIT)),
            () -> assertTrue(isNotRecruiting(PREPARE)),
            () -> assertTrue(isNotRecruiting(FINISH))
        );
    }
}
