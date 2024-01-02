package nextstep.courses.domain;

import nextstep.courses.CannotApproveException;
import nextstep.courses.CannotEnrollException;
import nextstep.courses.domain.session.Session;
import nextstep.courses.domain.session.SessionCondition;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.NsUserSessionsTest.*;
import static nextstep.courses.domain.SessionPeriodTest.NORMAL_SESSION_PERIOD;
import static nextstep.courses.domain.SessionStatusTest.RECRUITING_SESSION_STATUS;
import static nextstep.payments.domain.PaymentTest.FREE_1;
import static nextstep.payments.domain.PaymentTest.PAID_1;
import static nextstep.users.domain.NsUserTest.TEACHER_1;
import static nextstep.users.domain.NsUserTest.TEACHER_2;
import static org.assertj.core.api.Assertions.*;
import static org.junit.jupiter.api.Assertions.assertDoesNotThrow;
import static org.junit.jupiter.api.Assertions.fail;

public class SessionTest {

    public static final Session FREE_SESSION_1 = makeSession(new SessionCondition(0L, 100L));
    public static final Session FREE_SESSION_2 = makeSession(new SessionCondition(0L, 2L));
    public static final Session PAID_SESSION = makeSession(new SessionCondition(800_000L, 120L));

    private static Session makeSession(SessionCondition sessionPaymentCondition) {
        return new Session(1L, 1L, 1L, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition, true, 3L)
                .with(NS_USER_SESSIONS);
    }

    @Test
    @DisplayName("생성_null 이거나 0_throw IllegalArgumentException")
    void 생성_validate() {
        SessionCondition sessionPaymentCondition = new SessionCondition(0L, 0L);
        assertThatThrownBy(() -> new Session(0L, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition, true, 3L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
        assertThatThrownBy(() -> new Session(null, NORMAL_SESSION_PERIOD, RECRUITING_SESSION_STATUS, sessionPaymentCondition, true, 3L))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("과정 ID 값은 빈 값이거나 0이 올 수 없습니다.");
    }

    @Test
    void 무료강의_enroll() {
        try {
            assertThat(FREE_SESSION_1.enroll(FREE_1)).isEqualTo(new NsUserSession(FREE_1.getSessionId(), FREE_1.getNsUserId(), EnrollmentStatus.WAITING));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void 유료강의_enroll() {
        try {
            assertThat(PAID_SESSION.enroll(PAID_1)).isEqualTo(new NsUserSession(PAID_1.getSessionId(), PAID_1.getNsUserId(), EnrollmentStatus.WAITING));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void 수강신청_승인() {
        assertDoesNotThrow(() -> FREE_SESSION_1.canChangeEnrollmentStatus(TEACHER_1, NS_USER_SESSION_4_APPROVED));
        assertThatExceptionOfType(CannotApproveException.class)
                .isThrownBy(() -> FREE_SESSION_1.canChangeEnrollmentStatus(TEACHER_2, NS_USER_SESSION_4_APPROVED))
                .withMessage("강사 정보가 일치하지 않습니다.");
        assertThatExceptionOfType(CannotApproveException.class)
                .isThrownBy(() -> FREE_SESSION_2.canChangeEnrollmentStatus(TEACHER_1, NS_USER_SESSION_4_APPROVED))
                .withMessage("인원을 추가로 승인할 수 없습니다.");
    }

    @Test
    void 수강신청_취소() {
        assertDoesNotThrow(() -> FREE_SESSION_1.canChangeEnrollmentStatus(TEACHER_1, NS_USER_SESSION_4_REJECTED));
        assertThatExceptionOfType(CannotApproveException.class)
                .isThrownBy(() -> FREE_SESSION_1.canChangeEnrollmentStatus(TEACHER_2, NS_USER_SESSION_4_REJECTED))
                .withMessage("강사 정보가 일치하지 않습니다.");
        assertDoesNotThrow(() -> FREE_SESSION_2.canChangeEnrollmentStatus(TEACHER_1, NS_USER_SESSION_4_REJECTED));

    }
}
