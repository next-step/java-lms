package nextstep.courses.domain.session;

import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.courses.exception.session.InvalidPaymentAmountException;
import nextstep.courses.exception.session.InvalidSessionStateException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class SessionTest {

    @Test
    @DisplayName("강의가 준비중인 경우 수강신청이 불가능 하다")
    public void session_state_preparing() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        Session preparingSession = session.preparing();

        Assertions.assertThatThrownBy(() -> preparingSession.enroll(NsUserTest.JAVAJIGI, new Payment(1_000L)))
                .isInstanceOf(InvalidSessionStateException.class);
    }

    @Test
    @DisplayName("강의가 종료인 경우 수강신청이 불가능 하다")
    public void session_state_end() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        Session endSession = session.end();

        Assertions.assertThatThrownBy(() -> endSession.enroll(NsUserTest.JAVAJIGI, new Payment(1_000L)))
                .isInstanceOf(InvalidSessionStateException.class);
    }

    @Test
    @DisplayName("유료 강의는 최대 수강인원을 초과한 경우 등록이 불가능하다.")
    public void validate_enrollmentMax() {
        Session session = 세션_최대_수강인원_생성됨();

        Assertions.assertThatThrownBy(() -> session.enroll(NsUserTest.SANJIGI, new Payment(1_000L)))
                .isInstanceOf(EnrollmentMaxExceededException.class);
    }

    private Session 세션_최대_수강인원_생성됨() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        Session recruitingSession = session.recruiting();
        recruitingSession.enroll(NsUserTest.JAVAJIGI, new Payment());
        return recruitingSession;
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    public void validate_payment() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        Session recruitingSession = session.recruiting();

        Assertions.assertThatThrownBy(() -> recruitingSession.enroll(NsUserTest.SANJIGI, new Payment(100L)))
                .isInstanceOf(InvalidPaymentAmountException.class);
    }
}
