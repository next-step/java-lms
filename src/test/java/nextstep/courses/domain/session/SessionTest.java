package nextstep.courses.domain.session;

import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.courses.exception.session.InvalidPaymentAmountException;
import nextstep.courses.exception.session.InvalidSessionStateException;
import nextstep.courses.type.RecruitState;
import nextstep.courses.type.SessionState;
import nextstep.courses.type.SessionType;
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
        session.preparing();

        Assertions.assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, new Payment(1_000L)))
                .isInstanceOf(InvalidSessionStateException.class);
    }

    @Test
    @DisplayName("강의가 종료인 경우 수강신청이 불가능 하다")
    public void session_state_end() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        session.end();

        Assertions.assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, new Payment(1_000L)))
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
        session.ongoing();
        session.recruiting();
        session.enroll(NsUserTest.JAVAJIGI, new Payment());
        return session;
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    public void validate_payment() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        session.ongoing();
        session.recruiting();

        Assertions.assertThatThrownBy(() -> session.enroll(NsUserTest.SANJIGI, new Payment(100L)))
                .isInstanceOf(InvalidPaymentAmountException.class);
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    public void no_recruit() {
        Session session = Session.of(999999L, SessionType.PAID, SessionState.ONGOING, RecruitState.CLOSED, Period.from(), 1000L, 1000L, null, null);
        session.ongoing();

        Assertions.assertThatThrownBy(() -> session.enroll(NsUserTest.SANJIGI, new Payment(100L)))
                .isInstanceOf(InvalidSessionStateException.class);
    }
}
