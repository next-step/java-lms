package nextstep.payments;

import nextstep.payments.domain.EnrollmentPolicy;
import nextstep.payments.domain.FreeEnrollmentPolicy;
import nextstep.payments.domain.PaidEnrollmentPolicy;
import org.junit.jupiter.api.Test;

import static nextstep.payments.PaymentTest.PAYMENT_1000;
import static nextstep.payments.PaymentTest.PAYMENT_FREE;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class EnrollmentPolicyTest {

    @Test
    void 무료_강의_최대_수강_인원_제한이_없다() {
        EnrollmentPolicy enrollmentPolicy = new FreeEnrollmentPolicy();
        assertTrue(enrollmentPolicy.canEnroll(9999, PAYMENT_FREE));
    }

    @Test
    void 유료_강의_최대_수강_인원_제한이_있다() {
        EnrollmentPolicy enrollmentPolicy = new PaidEnrollmentPolicy(10, 1000);
        assertTrue(enrollmentPolicy.canEnroll(3, PAYMENT_1000));
        assertFalse(enrollmentPolicy.canEnroll(10, PAYMENT_1000));
    }

    @Test
    void 유료_강의_수강료가_같으면_수강할_수_있다() {
        EnrollmentPolicy enrollmentPolicy = new PaidEnrollmentPolicy(10, 1000);
        assertTrue(enrollmentPolicy.canEnroll(3, PAYMENT_1000));
    }

    @Test
    void 유료_강의_수강료가_다르면_수강할_수_없다() {
        EnrollmentPolicy enrollmentPolicy = new PaidEnrollmentPolicy(10, 5000);
        assertFalse(enrollmentPolicy.canEnroll(3, PAYMENT_1000));
    }

}
