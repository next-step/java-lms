package nextstep.courses.domain;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class SessionTest {
    @Test
    public void add_NotOpen() {
        NsUser user = new NsUser(1L, "userId1", "password", "name", "email");
        Session session = new Session(1L, "제목", new Period(LocalDateTime.now(), LocalDateTime.now()), "url", true, SessionStatus.CLOSED, new Students(), 30L);
        assertThatIllegalStateException().isThrownBy(() -> session.add(user));
    }

    @Test
    public void add_Full() {
        Session session = new Session(1L, "제목", new Period(LocalDateTime.now(), LocalDateTime.now()), "url", true, SessionStatus.OPEN, new Students(), 2L);
        session.add(new NsUser(1L, "userId1", "password", "name", "email"));
        session.add(new NsUser(2L, "userId2", "password", "name", "email"));
        NsUser user = new NsUser(3L, "userId3", "password", "name", "email");
        assertThatIllegalStateException().isThrownBy(() -> session.add(user));
    }
}
