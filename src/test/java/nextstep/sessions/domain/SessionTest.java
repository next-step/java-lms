package nextstep.sessions.domain;

import nextstep.sessions.exception.AttendeeException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionTest {

    private Session session;
    private NsUser user;
    private NsUser user2;

    @BeforeEach
    void setUp() {
        session = new Session.Builder()
                .id(1L)
                .courseId(1L)
                .imageId(1L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .maxAttendees(1)
                .type(SessionType.PAID)
                .status(SessionStatus.OPEN)
                .price(10000L)
                .build();
        user = new NsUser(1L, "tony", "1234", "ahn", "a@google.com");
        user2 = new NsUser(2L, "aaa", "1234", "bbb", "2@google.com");
    }

    @Test
    void freeSessionMaxAttendees() {
        Session freeSession = new Session.Builder()
                .id(1L)
                .courseId(1L)
                .imageId(1L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .maxAttendees(2)
                .type(SessionType.FREE)
                .status(SessionStatus.OPEN)
                .price(10000L)
                .build();
        freeSession.addAttendee(user);
        assertDoesNotThrow(() -> freeSession.addAttendee(user2));
    }

    @Test
    void paidSessionMaxAttendees() {
        session.addAttendee(user);
        assertThrows(AttendeeException.class, () -> session.addAttendee(user2));
    }

    @Test
    void addAttendee() {
        session.addAttendee(user);
        assertThat(session.getCurrentAttendees()).isEqualTo(1);
    }

    @Test
    void notOpenSession() {
        Session closedSession = new Session.Builder()
                .id(1L)
                .courseId(1L)
                .imageId(1L)
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .maxAttendees(1)
                .type(SessionType.PAID)
                .status(SessionStatus.CLOSED)
                .price(10000L)
                .build();
        assertThrows(AttendeeException.class, () -> closedSession.addAttendee(user));
    }
}
