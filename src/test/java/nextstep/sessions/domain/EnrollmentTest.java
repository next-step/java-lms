package nextstep.sessions.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.payments.domain.PaymentTest;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.Test;

class EnrollmentTest {

    @Test
    void validateUserAndPayment() {
        assertThatThrownBy(() -> new Enrollment(NsUserTest.SANJIGI, PaymentTest.PAYMENT_1000))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessageContaining(Enrollment.ERROR_USER_PAYMENT_MISMATCH);
    }

}