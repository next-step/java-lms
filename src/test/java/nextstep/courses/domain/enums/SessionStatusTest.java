package nextstep.courses.domain.enums;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionStatusTest {

    @DisplayName("강의 상태가 모집중일때 true를 반환한다.")
    @Test
    void canEnroll_should_return_true_for_recruiting_status() {
        assertTrue(SessionStatus.RECRUITING.canEnroll());
    }

    @Test
    @DisplayName("강의 상태가 모집중이 아닐때 false를 반환한다.")
    void canEnroll_should_return_false_for_non_recruiting_statuses() {
        assertAll(
                () -> assertFalse(SessionStatus.PREPARING.canEnroll()),
                () -> assertFalse(SessionStatus.ENDED.canEnroll())
        );
    }

}