package nextstep.courses.domain.sessions;

import nextstep.payments.domain.Payment;
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
        session = new Session(1L, 1L, 1L, LocalDateTime.now(), LocalDateTime.now(), 1, SessionType.PAID, SessionStatus.OPEN, 10000L);
        user = new NsUser(1L, "tony", "1234", "ahn", "a@google.com");
        user2 = new NsUser(2L, "aaa", "1234", "bbb", "2@google.com");
        session.addPayment(new Payment("1", 1L, 1L, 10000L));
        session.addPayment(new Payment("2", 1L, 2L, 10001L));
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

    @Test
    void paymentNotExists() {
        NsUser notExistUser = new NsUser(11L, "abc", "123", "na", "test@naver.com");
        assertThrows(IllegalStateException.class, () -> session.addAttendee(notExistUser));
    }

    @Test
    void paymentAmountNotEqual() {
        assertThrows(IllegalStateException.class, () -> session.addAttendee(user2));
    }

    @Test
    void addAttendee() {
        session.addAttendee(user);
        assertThat(session.getAttendeesSize()).isEqualTo(1);
    }
}
