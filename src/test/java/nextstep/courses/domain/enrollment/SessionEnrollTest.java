package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.enrollment.engine.SessionEnroll;
import nextstep.courses.domain.status.SessionStatus;
import nextstep.courses.exception.SessionCapacityExceedException;
import nextstep.courses.exception.SessionFeeMismatchException;
import nextstep.courses.exception.SessionStatusCannotEnrollmentException;
import nextstep.payments.domain.Payment;
import nextstep.payments.exception.PaymentAmountExistException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;
import static nextstep.courses.domain.fixture.NsUserFixture.nsUser;
import static nextstep.courses.domain.fixture.PaymentFixture.payment;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.freeSessionEnrollment;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.paidSessionEnrollment;
import static nextstep.courses.domain.fixture.SessionStatusFixture.status;
import static nextstep.courses.domain.status.ProgressStatus.PREPARING;
import static nextstep.courses.domain.status.RecruitmentStatus.NOT_RECRUITMENT;
import static nextstep.courses.domain.status.RecruitmentStatus.RECRUITMENT;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionEnrollTest {

    @Test
    @DisplayName("[성공] 무료 강의 신청을 만족한다.")
    void 무료_강의_신청_만족() {
        SessionStatus status = status(PREPARING, RECRUITMENT);
        SessionEnroll enrollment = freeSessionEnrollment(status);

        Payment payment = payment(0L);

        assertThatNoException().isThrownBy(() -> enrollment.satisfyEnrollment(payment));
    }

    @Test
    @DisplayName("[실패] 무료 강의 신청 시 결제 금액이 존재한다면 PaymentAmountExistException 예외가 발생한다.")
    void 무료_강의_신청_결제_금액_존재() {
        SessionEnroll enrollment = freeSessionEnrollment(status());

        Payment payment = payment(1L);

        assertThatExceptionOfType(PaymentAmountExistException.class)
                .isThrownBy(() -> enrollment.satisfyFee(payment));
    }

    @Test
    @DisplayName("[성공] 유료 강의 신청을 만족한다.")
    void 유료_강의_신청_만족() {
        SessionStatus status = status(PREPARING, RECRUITMENT);
        SessionEnroll enrollment = paidSessionEnrollment(status, 10, 800_000L);

        Payment payment = payment(800_000L);

        assertThatNoException().isThrownBy(() -> enrollment.satisfyEnrollment(payment));
    }

    @Test
    @DisplayName("[실패] 강의 신청 시 수강신청 가능한 상태가 아니라면 SessionCapacityExceedException 예외가 발생한다.")
    void 강의_신청_수강신청_불가능() {
        SessionStatus status = status(PREPARING, NOT_RECRUITMENT);
        SessionEnroll enrollment = freeSessionEnrollment(status);

        assertThatExceptionOfType(SessionStatusCannotEnrollmentException.class)
                .isThrownBy(enrollment::satisfyStatus);
    }

    @Test
    @DisplayName("[실패] 유료 강의 신청 시 결제 수강 인원이 초과 된다면 SessionCapacityExceedException 예외가 발생한다.")
    void 유료_강의_신청_수강_인원_초과() {
        SessionEnroll enrollment = paidSessionEnrollment(status(), 0, 800_000L);

        assertThatExceptionOfType(SessionCapacityExceedException.class)
                .isThrownBy(enrollment::satisfyCapacity);
    }

    @Test
    @DisplayName("[실패] 유료 강의 신청 시 결제 금액이 수강료와 불일치 한다면 SessionFeeMismatchException 예외가 발생한다.")
    void 유료_강의_신청_결제_금액_불일치() {
        SessionEnroll enrollment = paidSessionEnrollment(status(), 0, 800_000L);
        Payment payment = payment(100_000L);

        assertThatExceptionOfType(SessionFeeMismatchException.class)
                .isThrownBy(() -> enrollment.satisfyFee(payment));
    }

    @Test
    @DisplayName("[성공] 무료 강의를 신청한다.")
    void 무료_강의_신청() {
        SessionStatus status = status(PREPARING, RECRUITMENT);
        SessionEnroll enrollment = freeSessionEnrollment(status);

        NsUser user = nsUser();
        Payment payment = payment(0L);

        assertThatNoException()
                .isThrownBy(() -> enrollment.enroll(SESSION_ID, user, payment));
    }

    @Test
    @DisplayName("[성공] 유료 강의를 신청한다.")
    void 유료_강의_신청() {
        SessionStatus status = status(PREPARING, RECRUITMENT);
        SessionEnroll enrollment = paidSessionEnrollment(status, 10, 800_000L);

        NsUser user = nsUser();
        Payment payment = payment(800_000L);

        assertThatNoException()
                .isThrownBy(() -> enrollment.enroll(SESSION_ID, user, payment));
    }

}
