package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.fail;

class SessionTest {
    private static Course course = new Course("testCourse", 1L);
    private static SessionPaymentInfo PI1 = new SessionPaymentInfo(0L, 0L, 0L);
    private static SessionPaymentInfo PI2 = new SessionPaymentInfo(800_000L, 120L, 0L);
    private static LocalDateTime startedAt = LocalDateTime.of(2023, 11, 1, 0, 0);
    private static LocalDateTime finishedAt = LocalDateTime.of(2023, 11, 30, 23, 59, 59);

    @Test
    void 무료강의_enroll() {
        Payment payment = new Payment(1L, 0L, 0L);
        Session freeSession = new Session(1L, new CoverImage(), startedAt, finishedAt, SessionStatus.RECRUITING, PI1);
        try {
            assertThat(freeSession.enroll(payment)).isEqualTo(new NsUserSession(1L, 0L));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void 유료강의_enroll() {
        Payment payment = new Payment(1L, 0L, 800_000L);
        Session paidSession = new Session(1L, new CoverImage(), startedAt, finishedAt, SessionStatus.RECRUITING, PI2);
        try {
            assertThat(paidSession.enroll(payment)).isEqualTo(new NsUserSession(1L, 0L));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

}