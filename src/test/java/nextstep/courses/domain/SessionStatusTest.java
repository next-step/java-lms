package nextstep.courses.domain;

import nextstep.courses.CanNotApplySessionStatusException;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;

class SessionStatusTest {

    @Test
    @DisplayName("RECRUITING 때는 신청이 가능 하다")
    void canApply() {
        SessionStatus sessionStatus = SessionStatus.RECRUITING;

        assertDoesNotThrow(sessionStatus::validateApply);
    }

    @ParameterizedTest(name = "{0} 때는 예외 처리가 된다")
    @CsvSource({"PREPARING", "END"})
    void canApply2(SessionStatus input) {
        Assertions.assertThrows(CanNotApplySessionStatusException.class, input::validateApply,
                "수강 신청이 가능한 상태가 아닙니다.");
    }

}
