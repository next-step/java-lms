package nextstep.courses.domain;

import nextstep.courses.CannotEnrollException;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SessionConditionTest.FREE_SESSION_CONDITION_1;
import static nextstep.courses.domain.SessionConditionTest.PAID_SESSION_CONDITION;
import static nextstep.courses.domain.SessionStatusTest.RECRUITING_SESSION_STATUS;
import static nextstep.payments.domain.PaymentTest.FREE_1;
import static nextstep.payments.domain.PaymentTest.PAID_1;
import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.fail;

class EnrollmentTest {

    @Test
    void 무료강의_enroll() {
        Enrollment enrollment = new Enrollment(RECRUITING_SESSION_STATUS, FREE_SESSION_CONDITION_1, true);
        try {
            assertThat(enrollment.enroll(FREE_1))
                    .isEqualTo(new Student(FREE_1.getSessionId(), FREE_1.getNsUserId(), EnrollmentStatus.WAITING));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }

    @Test
    void 유료강의_enroll() {
        Enrollment enrollment = new Enrollment(RECRUITING_SESSION_STATUS, PAID_SESSION_CONDITION, true);
        try {
            assertThat(enrollment.enroll(PAID_1))
                    .isEqualTo(new Student(PAID_1.getSessionId(), PAID_1.getNsUserId(), EnrollmentStatus.WAITING));
        } catch (CannotEnrollException e) {
            fail(e.getMessage());
        }
    }
}
