package nextstep.courses.domain.session;

import nextstep.courses.domain.session.enrollment.Enrollment;
import nextstep.courses.exception.session.EnrollmentMaxExceededException;
import nextstep.courses.exception.session.InvalidPaymentAmountException;
import nextstep.courses.exception.session.InvalidProgressStateException;
import nextstep.courses.type.ProgressState;
import nextstep.courses.type.RecruitState;
import nextstep.courses.type.SessionType;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class SessionTest {

    @Test
    @DisplayName("강의가 준비중인 경우 수강신청이 불가능 하다")
    public void progress_state_preparing() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        session.preparing();

        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, new Payment(1_000L)))
                .isInstanceOf(InvalidProgressStateException.class);
    }

    @Test
    @DisplayName("강의가 종료인 경우 수강신청이 불가능 하다")
    public void progress_state_end() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        session.end();

        assertThatThrownBy(() -> session.enroll(NsUserTest.JAVAJIGI, new Payment(1_000L)))
                .isInstanceOf(InvalidProgressStateException.class);
    }

    @Test
    @DisplayName("유료 강의는 최대 수강인원을 초과한 경우 등록이 불가능하다.")
    public void validate_enrollmentMax() {
        Session session = 세션_최대_수강인원_생성됨();

        assertThatThrownBy(() -> session.enroll(NsUserTest.SANJIGI, new Payment(1_000L)))
                .isInstanceOf(EnrollmentMaxExceededException.class);
    }

    private Session 세션_최대_수강인원_생성됨() {
        return 강의_생성_및_수강신청(NsUserTest.JAVAJIGI, new Payment());
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    public void validate_payment() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        session.ongoing();
        session.recruiting();

        assertThatThrownBy(() -> session.enroll(NsUserTest.SANJIGI, new Payment(100L)))
                .isInstanceOf(InvalidPaymentAmountException.class);
    }

    @Test
    @DisplayName("강의 수강신청은 강의 상태가 모집중일 때만 가능하다.")
    public void no_recruit() {
        Session session = Session.of(999999L,
                SessionInfo.of(SessionType.PAID, Period.from()),
                Enrollment.of(ProgressState.ONGOING, RecruitState.CLOSED, 1000L, 1000L, SessionType.PAID),
                null, null);
        session.ongoing();

        assertThatThrownBy(() -> session.enroll(NsUserTest.SANJIGI, new Payment(100L)))
                .isInstanceOf(InvalidProgressStateException.class);
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발된 인원에 대해서 수강 승인이 가능하다.")
    public void session_approval() {
        Session session = 강의_생성_및_수강신청(NsUserTest.SANJIGI, new Payment(1000L));
        Student student = session.approvalSession(NsUserTest.SANJIGI);

        assertThat(student.sessionApproval().isApproval()).isTrue();
    }

    @Test
    @DisplayName("강사는 수강신청한 사람 중 선발되지 않은 사람은 수강을 취소할 수 있어야 한다.")
    public void session_cancel() {
        Session session = 강의_생성_및_수강신청(NsUserTest.SANJIGI, new Payment(1000L));
        Student student = session.approvalCancel(NsUserTest.SANJIGI);

        assertThat(student.sessionApproval().isCancel()).isTrue();
    }

    @Test
    @DisplayName("승인/취소시 수강생이 존재하지 않는 경우 예외가 발생한다.")
    public void session_approval_and_student_not_exist() {
        Session session = 강의_생성();

        assertThatThrownBy(() -> session.approvalSession(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
        assertThatThrownBy(() -> session.approvalCancel(NsUserTest.SANJIGI))
                .isInstanceOf(IllegalArgumentException.class);
    }

    private Session 강의_생성_및_수강신청(NsUser user, Payment payment) {
        Session session = 강의_생성();
        session.enroll(user, payment);
        return session;
    }

    private Session 강의_생성() {
        Session session = Session.ofPaid(Period.from(), Image.from(), 1_000L, 1L);
        session.ongoing();
        session.recruiting();

        return session;
    }
}
