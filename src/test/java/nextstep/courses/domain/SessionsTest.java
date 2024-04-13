package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class SessionsTest {

    @Test
    @DisplayName("세선이 존재할 때, 세션 등록 성공 테스트")
    void enroll_session_test() {
        // given
        SessionInformation sessionInformation = new SessionInformation(SessionStatus.RECRUITING, new SessionEnrollment(), new SessionSchedule());
        Sessions sessions = new Sessions(1L, List.of(new Session(1L, new Image(), sessionInformation)));

        // when & then
        assertDoesNotThrow(() -> sessions.enroll(1L, 1L));
    }

    @Test
    @DisplayName("존재하지 않는 세션 등록 시도 시, 세션 등록 실패 테스트")
    void enroll_session_exception_test() {
        // given
        SessionInformation sessionInformation = new SessionInformation(SessionStatus.RECRUITING, new SessionEnrollment(), new SessionSchedule());
        Sessions sessions = new Sessions(1L, List.of(new Session(1L, new Image(), sessionInformation)));

        // when & then
        assertThrows(IllegalArgumentException.class, () -> sessions.enroll(1L, 2L));
    }

}
