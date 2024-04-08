package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.MismatchSessionFeeException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.PaymentFixture.payment;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.sessionEnrollment;
import static nextstep.courses.domain.fixture.SessionFeeFixture.SESSION_FEE;
import static nextstep.courses.exception.SessionExceptionMessage.CAPACITY_EXCEED;
import static nextstep.courses.exception.SessionExceptionMessage.PAYMENT_MISMATCH;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionEnrollmentConditionTest {

    @Test
    @DisplayName("[성공] 강의 수강에 제약이 없다.")
    void 강의_무조건() {
        SessionEnrollmentCondition condition = new SessionNoneCondition();
        assertThatNoException()
                .isThrownBy(() -> condition.satisfy(null));
    }

    @Test
    @DisplayName("[성공] 강의 최대 수강 인원을 초과할 수 없다.")
    void 강의_수강인원() {
        SessionEnrollment enrollment = sessionEnrollment(sessionCapacity());

        SessionEnrollmentCondition condition = new SessionCapacityCondition();
        assertThatNoException()
                .isThrownBy(() -> condition.satisfy(enrollment));
    }

    @Test
    @DisplayName("[실패] 최대 수강인원을 초과하면 ExceedSessionCapacityException 예외가 발생한다.")
    void 강의_수강인원_초과() {
        SessionCapacity capacity = sessionCapacity(10, 10);
        SessionEnrollment enrollment = sessionEnrollment(capacity);

        SessionEnrollmentCondition condition = new SessionCapacityCondition();
        assertThatExceptionOfType(ExceedSessionCapacityException.class)
                .isThrownBy(() -> condition.satisfy(enrollment))
                .withMessageContaining(CAPACITY_EXCEED.getMessage());
    }

    @Test
    @DisplayName("[성공] 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void 강의_수강료() {
        Payment payment = payment(SESSION_FEE);
        SessionEnrollment enrollment = sessionEnrollment(SESSION_FEE, SessionEnrollmentConditions.from(new SessionFeeCondition(payment)));

        SessionEnrollmentCondition condition = new SessionFeeCondition(payment);
        assertThatNoException()
                .isThrownBy(() -> condition.satisfy(enrollment));
    }

    @Test
    @DisplayName("[실패] 강의의 결제 금액과 수강료가 일치하지 않으면 MismatchSessionFeeException 예외가 발생한다.")
    void 강의_수강료_불일치() {
        Payment payment = payment(100_000L);
        SessionEnrollment enrollment = sessionEnrollment(SESSION_FEE, SessionEnrollmentConditions.from(new SessionFeeCondition(payment)));

        SessionEnrollmentCondition condition = new SessionFeeCondition(payment);
        assertThatExceptionOfType(MismatchSessionFeeException.class)
                .isThrownBy(() -> condition.satisfy(enrollment))
                .withMessageContaining(PAYMENT_MISMATCH.getMessage());
    }

}
