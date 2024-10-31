package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SessionStatus;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertTrue;

public class SessionStatusTest {

    @Test
    @DisplayName("강의 상태가 모집중일 때만 신청이 가능하다.")
    void 강의_상태_모집중(){
        SessionStatus sessionStatus = SessionStatus.RECRUITING;
        assertTrue(sessionStatus.isRecruiting());
    }
}
