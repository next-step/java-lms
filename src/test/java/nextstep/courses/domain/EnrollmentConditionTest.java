package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.MismatchSessionFeeException;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.SessionFixture.session;
import static nextstep.courses.exception.SessionExceptionMessage.CAPACITY_EXCEED;
import static nextstep.courses.exception.SessionExceptionMessage.PAYMENT_MISMATCH;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class EnrollmentConditionTest {

    @Test
    @DisplayName("[성공] 강의 수강에 제약이 없다.")
    void 강의_무조건() {
        EnrollmentCondition condition = new NoneCondition();
        assertThatNoException()
                .isThrownBy(() -> condition.isSatisfied(session()));
    }

    @Test
    @DisplayName("[성공] 강의 최대 수강 인원을 초과할 수 없다.")
    void 강의_수강인원() {
        EnrollmentCondition condition = new CapacityCondition();
        assertThatNoException()
                .isThrownBy(() -> condition.isSatisfied(session(new SessionCapacity(10))));
    }

    @Test
    @DisplayName("[실패] 최대 수강인원을 초과하면 ExceedSessionCapacityException 예외가 발생한다.")
    void 강의_수강인원_초과() {
        EnrollmentCondition condition = new CapacityCondition();
        assertThatExceptionOfType(ExceedSessionCapacityException.class)
                .isThrownBy(() -> condition.isSatisfied(session(new SessionCapacity(10, 10))))
                .withMessageContaining(CAPACITY_EXCEED.getMessage());
    }

    @Test
    @DisplayName("[성공] 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void 강의_수강료() {
        Payment payment = new Payment("paymentId", 0L, 0L, 800_000L);
        assertThatNoException()
                .isThrownBy(() -> (new FeeCondition(payment)).isSatisfied(session()));
    }

    @Test
    @DisplayName("[실패] 강의의 결제 금액과 수강료가 일치하지 않으면 MismatchSessionFeeException 예외가 발생한다.")
    void 강의_수강료_불일치() {
        Payment payment = new Payment("paymentId", 0L, 0L, 100_000L);
        assertThatExceptionOfType(MismatchSessionFeeException.class)
                .isThrownBy(() -> (new FeeCondition(payment)).isSatisfied(session()))
                .withMessageContaining(PAYMENT_MISMATCH.getMessage());
    }

}
