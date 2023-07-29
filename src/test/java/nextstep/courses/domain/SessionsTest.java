package nextstep.courses.domain;

import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class SessionsTest {

    private Sessions sessions;

    @BeforeEach
    void setUp() {
        sessions = new Sessions();

        sessions.addSession(new Session(0L, "TDD", 3, true));
        sessions.addSession(new Session(1L, "DDD", 3, false));
    }

    @Test
    void 강의_추가_null_체크() throws Exception {
        assertThatNullPointerException().isThrownBy(() -> sessions.addSession(null));
    }

    @Test
    void 강의_모집() {
        SessionStatus sessionStatus = sessions.startRecruiting(0L);

        assertThat(sessionStatus).isEqualTo(SessionStatus.RECRUITING);
    }

    @Test
    public void 수강신청_완료() throws Exception {
        sessions.startRecruiting(0L);

        assertThatNoException().isThrownBy(() -> sessions.enrolment(NsUserTest.JAVAJIGI, 0L));
    }

    @Test
    void 수강_신청_강의를_찾을수_없음() throws Exception{
        assertThatIllegalArgumentException().isThrownBy(() -> sessions.enrolment(NsUserTest.JAVAJIGI, 3L));
    }

    @Test
    void 강의_시작() {
        sessions.startRecruiting(0L);
        SessionStatus sessionStatus = sessions.startSession(0L);

        assertThat(sessionStatus).isEqualTo(SessionStatus.PROCEEDING);
    }

    @Test
    void 강의_종료() {
        sessions.startRecruiting(0L);
        sessions.startSession(0L);
        SessionStatus sessionStatus = sessions.endSession(0L);

        assertThat(sessionStatus).isEqualTo(SessionStatus.END);

    }
}
