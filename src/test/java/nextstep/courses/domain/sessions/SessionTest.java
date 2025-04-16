package nextstep.courses.domain.sessions;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.images.Image;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.assertThrows;

public class SessionTest {

    private Course course;
    private Session session;
    private NsUser user;
    private NsUser user2;
    private NsUser user3;
    private Payment payment;
    private Payment payment2;
    private Payment payment3;

    @BeforeEach
    void setUp() {
        course = new Course("test", 1L);
        session = new Session.Builder()
                .id(1L)
                .course(course)
                .image(new Image())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .maxAttendees(1)
                .type(SessionType.PAID)
                .status(SessionStatus.OPEN)
                .price(10000L)
                .build();
        user = new NsUser(1L, "tony", "1234", "ahn", "a@google.com");
        user2 = new NsUser(2L, "aaa", "1234", "bbb", "2@google.com");
        user3 = new NsUser(3L, "bbb", "1234", "ccc", "3@google.com");
        payment = new Payment("1", 1L, 1L, 10000L);
        payment2 = new Payment("2", 1L, 2L, 10000L);
        payment3 = new Payment("3", 1L, 3L, 10001L);
        session.addPayment(payment);
        session.addPayment(payment2);
        session.addPayment(payment3);
    }

    @Test
    void freeSessionMaxAttendees() {
        Session freeSession = new Session.Builder()
                .id(1L)
                .course(course)
                .image(new Image())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .maxAttendees(2)
                .type(SessionType.FREE)
                .status(SessionStatus.OPEN)
                .price(10000L)
                .build();
        freeSession.addPayment(payment);
        freeSession.addPayment(payment2);
        freeSession.addPayment(payment3);
        freeSession.addAttendee(user);
        assertDoesNotThrow(() -> freeSession.addAttendee(user2));
    }

    @Test
    void paidSessionMaxAttendees() {
        session.addAttendee(user);
        assertThrows(IllegalStateException.class, () -> session.addAttendee(user2));
    }

    @Test
    void paymentNotExists() {
        NsUser notExistUser = new NsUser(11L, "abc", "123", "na", "test@naver.com");
        assertThrows(IllegalStateException.class, () -> session.addAttendee(notExistUser));
    }

    @Test
    void paymentAmountNotEqual() {
        assertThrows(IllegalStateException.class, () -> session.addAttendee(user3));
    }

    @Test
    void addAttendee() {
        session.addAttendee(user);
        assertThat(session.getAttendeesSize()).isEqualTo(1);
    }

    @Test
    void notOpenSession() {
        Session closedSession = new Session.Builder()
                .id(1L)
                .course(course)
                .image(new Image())
                .startDate(LocalDateTime.now())
                .endDate(LocalDateTime.now())
                .maxAttendees(1)
                .type(SessionType.PAID)
                .status(SessionStatus.CLOSED)
                .price(10000L)
                .build();
        assertThrows(IllegalStateException.class, () -> closedSession.addAttendee(user));
    }
}
