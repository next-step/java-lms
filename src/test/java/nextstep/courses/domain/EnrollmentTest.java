package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE;
import static nextstep.courses.domain.SessionPeriodTest.NORMAL_SESSION_PERIOD;
import static nextstep.courses.domain.SessionTest.FREE_SESSION;
import static nextstep.courses.domain.SessionTest.PAID_SESSION;
import static nextstep.payments.domain.PaymentTest.FREE;
import static nextstep.payments.domain.PaymentTest.PAID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.fail;

class EnrollmentTest {
    @Test
    @DisplayName("enroll_SessionStatus.RECRUITING 외_throw CannotEnrollException")
    void 모집중이_아닌_강의_enroll() {
        Session preparingSession = new Session(1L, NORMAL_SESSION_PERIOD, SessionStatus.PREPARING, new SessionCondition());
        Enrollment enrollment = new Enrollment(preparingSession, new NsUserSessions(new ArrayList<>()));
        assertThatThrownBy(() -> enrollment.enroll(FREE))
                .isInstanceOf(CannotEnrollException.class)
                .hasMessage("강의가 모집중인 상태가 아닙니다.");
    }

    @Test
    void 무료강의_enroll() {
        Enrollment enrollment = new Enrollment(FREE_SESSION, new NsUserSessions(new ArrayList<>()));
        try {
            assertThat(enrollment.enroll(FREE)).isEqualTo(new NsUserSession(FREE.getSessionId(), FREE.getNsUserId()));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void 유료강의_enroll() {
        Enrollment enrollment = new Enrollment(PAID_SESSION, new NsUserSessions(new ArrayList<>()));
        try {
            assertThat(enrollment.enroll(PAID_1)).isEqualTo(new NsUserSession(PAID_1.getSessionId(), PAID_1.getNsUserId()));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }
}
