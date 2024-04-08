package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import nextstep.courses.domain.session.condition.SessionCapacityCondition;
import nextstep.courses.domain.session.condition.SessionCondition;
import nextstep.courses.domain.session.condition.SessionFeeCondition;
import nextstep.courses.domain.session.condition.SessionNoneCondition;
import nextstep.courses.exception.ExceedSessionCapacityException;
import nextstep.courses.exception.MismatchSessionFeeException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.fixture.SessionCapacityFixture.MAX_CAPACITY;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionFeeFixture.SESSION_FEE;
import static nextstep.courses.domain.fixture.SessionFeeFixture.sessionFee;
import static nextstep.courses.exception.SessionExceptionMessage.CAPACITY_EXCEED;
import static nextstep.courses.exception.SessionExceptionMessage.PAYMENT_MISMATCH;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionConditionTest {

    @Test
    @DisplayName("[성공] 강의 수강에 제약이 없다.")
    void 강의_무조건() {
        SessionCondition condition = new SessionNoneCondition();
        assertThatNoException()
                .isThrownBy(condition::satisfy);
    }

    @Test
    @DisplayName("[성공] 강의 최대 수강 인원을 초과할 수 없다.")
    void 강의_수강인원() throws ExceedSessionCapacityException {
        SessionCapacity capacity = sessionCapacity(MAX_CAPACITY);

        SessionCondition condition = new SessionCapacityCondition(capacity);
        assertThatNoException()
                .isThrownBy(condition::satisfy);
    }

    @Test
    @DisplayName("[실패] 최대 수강인원을 초과하면 ExceedSessionCapacityException 예외가 발생한다.")
    void 강의_수강인원_초과() throws ExceedSessionCapacityException {
        SessionCapacity capacity = sessionCapacity(0);

        SessionCondition condition = new SessionCapacityCondition(capacity);
        assertThatExceptionOfType(ExceedSessionCapacityException.class)
                .isThrownBy(condition::satisfy)
                .withMessageContaining(CAPACITY_EXCEED.getMessage());
    }

    @Test
    @DisplayName("[성공] 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void 강의_수강료() {
        SessionFee fee = sessionFee(SESSION_FEE);
        SessionFeeCondition condition =  new SessionFeeCondition(fee, SESSION_FEE);

        assertThatNoException().isThrownBy(condition::satisfy);
    }

    @Test
    @DisplayName("[실패] 강의의 결제 금액과 수강료가 일치하지 않으면 MismatchSessionFeeException 예외가 발생한다.")
    void 강의_수강료_불일치() {
        SessionFee fee = sessionFee(SESSION_FEE);
        SessionFeeCondition condition =  new SessionFeeCondition(fee, 100_000L);

        assertThatExceptionOfType(MismatchSessionFeeException.class)
                .isThrownBy(condition::satisfy)
                .withMessageContaining(PAYMENT_MISMATCH.getMessage());
    }

}
