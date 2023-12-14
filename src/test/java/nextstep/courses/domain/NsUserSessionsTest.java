package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

class NsUserSessionsTest {
    public static final NsUserSession NS_USER_SESSION_1 = new NsUserSession(1L, 1L);
    public static final NsUserSession NS_USER_SESSION_2 = new NsUserSession(1L, 2L);
    public static final NsUserSessions NS_USER_SESSIONS = new NsUserSessions(NS_USER_SESSION_1, NS_USER_SESSION_2);

    @Test
    void isExist() {
        assertTrue(NS_USER_SESSIONS.isExist(1L, 1L));
        assertFalse(NS_USER_SESSIONS.isExist(1L, 3L));
    }
}
