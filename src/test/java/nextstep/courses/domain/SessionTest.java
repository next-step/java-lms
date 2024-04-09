package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import java.time.LocalDateTime;
import nextstep.courses.CannotRegisterException;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    void 무료강의_신청() throws CannotRegisterException {
        LocalDateTime now = LocalDateTime.now();
        Session session = new Session(1L, "lms", SessionType.FREE, SessionState.RECRUITING, "test.jpg", now.plusDays(5), now.plusDays(30));
        session.register(NsUserTest.JAVAJIGI);
    }

    @Test
    void 무료강의_신청_모집중_이외에는_예외발생() {
        LocalDateTime now = LocalDateTime.now();
        Session session1 = new Session(1L, "lms", SessionType.FREE, SessionState.READY, "test.jpg", now.plusDays(5), now.plusDays(30));
        assertThatThrownBy(() -> session1.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);

        Session session2 = new Session(1L, "lms", SessionType.FREE, SessionState.END, "test.jpg", now.plusDays(5), now.plusDays(30));
        assertThatThrownBy(() -> session2.register(NsUserTest.JAVAJIGI))
                .isInstanceOf(CannotRegisterException.class);
    }
}
