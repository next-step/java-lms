package nextstep.courses.domain;

import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static nextstep.courses.domain.fixture.PaymentFixture.payment;
import static nextstep.courses.domain.fixture.SessionEnrollmentFixture.sessionEnrollment;
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
        SessionEnrollment enrollment = sessionEnrollment(SessionEnrollmentConditions.of(
                List.of(new SessionCapacityCondition(), new SessionFeeCondition(payment())))
        );
        assertThatNoException().isThrownBy(enrollment::enroll);
    }

}
