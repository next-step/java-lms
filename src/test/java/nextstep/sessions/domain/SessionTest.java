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
                .currentAttendees(0)
                .type(SessionType.PAID)
                .status(SessionStatus.OPEN)
                .price(10000L)
                .build();
        user = new NsUser.Builder()
                .id(1L)
                .sessionId(1L)
                .userId("tony")
                .password("1234")
                .name("ahn")
                .email("a@google.com")
                .build();
        user2 = new NsUser.Builder()
                .id(2L)
                .sessionId(1L)
                .userId("aaa")
                .password("1234")
                .name("bbb")
                .email("2@google.com")
                .build();
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
                .currentAttendees(0)
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
                .currentAttendees(0)
                .type(SessionType.PAID)
                .status(SessionStatus.CLOSED)
                .price(10000L)
                .build();
        assertThrows(AttendeeException.class, () -> closedSession.addAttendee(user));
    }
}
