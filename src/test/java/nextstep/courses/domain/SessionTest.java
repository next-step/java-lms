package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.Test;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.*;

class SessionTest {
    private static final Payment PAYMENT = new Payment(1L, 0L, 800_000L);
    private static final LocalDateTime STARTED_AT = LocalDateTime.of(2023, 11, 1, 0, 0);
    private static final LocalDateTime FINISHED_AT = LocalDateTime.of(2023, 11, 30, 23, 59, 59);

    private Session makeSession(SessionPaymentInfo sessionPaymentInfo) {
        return new Session(0L, new CoverImage(), STARTED_AT, FINISHED_AT, SessionStatus.RECRUITING, sessionPaymentInfo);
    }

    @Test
    void 무료강의_enroll() {
        SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(0L, 0L, 0L);
        Session freeSession = makeSession(sessionPaymentInfo);
        try {
            assertThat(freeSession.enroll(PAYMENT)).isEqualTo(new NsUserSession(1L, 0L));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void 유료강의_enroll() {
        SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(800_000L, 120L, 0L);
        Session paidSession = makeSession(sessionPaymentInfo);
        try {
            assertThat(paidSession.enroll(PAYMENT)).isEqualTo(new NsUserSession(1L, 0L));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void 유료_최대수강인원_초과() {
        SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(800_000L, 120L, 120L);
        Session paidSession = makeSession(sessionPaymentInfo);
        assertThatThrownBy(() -> paidSession.enroll(PAYMENT)).isInstanceOf(CannotEnrollException.class).hasMessage("수강 인원을 초과했습니다.");
    }

    @Test
    void 유료_결제금액_불일치() {
        SessionPaymentInfo sessionPaymentInfo = new SessionPaymentInfo(500_000L, 120L, 0L);
        Session paidSession = makeSession(sessionPaymentInfo);
        assertThatThrownBy(() -> paidSession.enroll(PAYMENT)).isInstanceOf(CannotEnrollException.class).hasMessage("결제 금액이 일치하지 않습니다.");
    }
}