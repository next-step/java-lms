package nextstep.courses.domain;

import nextstep.courses.domain.field.CoverImage;
import nextstep.courses.domain.field.ImageType;
import nextstep.courses.domain.field.SessionStatus;
import nextstep.courses.domain.field.SessionType;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
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
    @DisplayName("세션 강의 등록시 코스에 포함된 세션 강의 숫자 검증")
    public void assertSessionRegister() {
        freeSession.openSession();
        freeSession.registerForFreeSession(course);

        assertThat(course.sessionList().numbOfSessions()).isEqualTo(1);
    }

    @Test
    @DisplayName("무료 강의 상태가 open인지 검증")
    public void assertIsSessionOpen() {
        freeSession.openSession();
        freeSession.registerForFreeSession(course);

        assertThat(freeSession.isOpen()).isEqualTo(true);
    }

    @Test
    @DisplayName("무료 세션 강의가 무료 강의인지 검증")
    public void assertFreeSession() {
        freeSession.registerForFreeSession(course);

        assertThat(freeSession.isFree()).isEqualTo(true);
    }

    @Test
    @DisplayName("유료 세션 강의에 대한 강의료가 일치하는지 검증")
    public void assertPaidSession() {
        Payment payment = new Payment("1", 1L, 123L, 100L);
        paidSession.registerForPaidSession(course, payment);

        assertThat(paidSession.isPaymentCorrect(payment)).isEqualTo(true);
    }

    @Test
    @DisplayName("세션 강의 오픈 슬랏이 세션 등록할때 감소하는지에 대한 검증")
    public void assertAvailableSlots() {
        Payment payment = new Payment("1", 1L, 123L, 100L);
        paidSession.registerForPaidSession(course, payment);

        assertThat(paidSession.isAvailable()).isFalse();
    }
}