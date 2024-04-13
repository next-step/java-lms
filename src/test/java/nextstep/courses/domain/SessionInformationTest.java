package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class SessionInformationTest {

    @Test
    @DisplayName("모집중이지 않은 강의를 수강신청하면 에러가 난다")
    void enroll_not_recruiting() {
        // given
        SessionInformation sessionInformation = new SessionInformation(SessionStatus.READY, new SessionEnrollment(), new SessionSchedule());

        // when & then
        assertThrows(IllegalArgumentException.class, () -> sessionInformation.enroll(1L, 1L));
    }

    @Test
    @DisplayName("모집중인 강의를 수강신청하면 성공한다")
    void enroll_recruiting() {
        // given
        SessionInformation sessionInformation = new SessionInformation(SessionStatus.RECRUITING, new SessionEnrollment(), new SessionSchedule());

        // when & then
        assertDoesNotThrow(() -> sessionInformation.enroll(1L, 1L));
    }
}
