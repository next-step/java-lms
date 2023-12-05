package nextstep.courses.domain;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.ImageType;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class SessionTest {
    private Course course;
    private Session freeSession;
    private Session paidSession;

    @BeforeEach
    public void setup() {
        course = new Course("test", 1L);
        CoverImage coverImage = new CoverImage(0.5, 350, 250, ImageType.GIF);
        freeSession = new Session(1L, coverImage, SessionType.FREE, SessionStatus.CLOSED);
        paidSession = new Session(1L, coverImage, SessionType.PAID, SessionStatus.CLOSED, 100, 1);
    }


    @Test
    public void assertSessionRegister() {
        freeSession.register(course);

        assertThat(course.sessionList().numbOfSessions()).isEqualTo(1);
    }

    @Test
    public void assertIsSessionOpen() {
        freeSession.openSession();
        freeSession.register(course);

        assertThat(freeSession.isOpen()).isEqualTo(true);
    }

    @Test
    public void assertFreeSession() {
        freeSession.register(course);

        assertThat(freeSession.isFree()).isEqualTo(true);
    }

    @Test
    public void assertPaidSession() {
        paidSession.register(course);
        Payment payment = new Payment("1", 1L, 123L, 100L);

        assertThat(paidSession.isPaymentCorrect(payment)).isEqualTo(true);
    }

    @Test
    public void assertAvailableSlots() {
        paidSession.register(course);

        assertThat(paidSession.isAvailable()).isFalse();
        System.out.println(course.sessionList());
    }
}