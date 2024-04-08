package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import nextstep.payments.domain.Payment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.fixture.PaymentFixture.payment;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.MAX_CAPACITY;
import static nextstep.courses.domain.fixture.SessionCapacityFixture.sessionCapacity;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.sessionEnrollment;
import static nextstep.courses.domain.fixture.SessionFeeFixture.SESSION_FEE;
import static nextstep.courses.domain.fixture.SessionFeeFixture.sessionFee;
import static org.assertj.core.api.Assertions.assertThatNoException;

public class SessionEnrollmentTest {

    @Test
    @DisplayName("[성공] 무료 강의를 수강신청 한다.")
    void 무료_강의_수강신청() {
        SessionEnrollment enrollment = sessionEnrollment(SessionEnrollmentConditions.from(new SessionNoneCondition()));
        assertThatNoException().isThrownBy(enrollment::enroll);
    }

    @Test
    @DisplayName("[성공] 유료 강의를 수강신청 한다.")
    void 유료_강의_수강신청() {
        Payment payment = payment(SESSION_FEE);
        SessionCapacity capacity = sessionCapacity(MAX_CAPACITY);
        SessionFee fee = sessionFee(SESSION_FEE);
        SessionEnrollmentConditions conditions = SessionEnrollmentConditions.of(List.of(new SessionCapacityCondition(), new SessionFeeCondition(payment)));
        SessionEnrollment enrollment = sessionEnrollment(capacity, fee, conditions);

        assertThatNoException().isThrownBy(enrollment::enroll);
    }

}
