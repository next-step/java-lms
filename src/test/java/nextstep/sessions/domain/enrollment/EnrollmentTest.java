package nextstep.sessions.domain.enrollment;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.payments.domain.PaymentTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    public static Enrollment E1 = new Enrollment(NsUserTest.JAVAJIGI, PaymentTest.PAYMENT_1000);
    public static Enrollment E2 = new Enrollment(NsUserTest.SANJIGI, PaymentTest.PAYMENT_SAN_1000);


    @Test
    void validateUserAndPayment() {
        assertThatThrownBy(() -> new Enrollment(NsUserTest.SANJIGI, PaymentTest.PAYMENT_1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Enrollment.ERROR_USER_PAYMENT_MISMATCH);
    }

}