package nextstep.courses.domain.sessions;

import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionTest {

    private Session session;

    @BeforeEach
    void setUp() {
        session = new Session(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 1, SessionType.PAID, SessionStatus.OPEN, 10000L);
    }

    @Test
    void freeSessionMaxAttendees() {
        Session freeSession = new Session(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 1, SessionType.FREE, SessionStatus.OPEN, 0L);
        freeSession.addAttendee(new NsUser());
        assertDoesNotThrow(() -> freeSession.addAttendee(new NsUser()));
    }

    @Test
    void paidSessionMaxAttendees() {
        session.addAttendee(new NsUser());
        assertThrows(IllegalStateException.class, () -> session.addAttendee(new NsUser()));
    }
}
