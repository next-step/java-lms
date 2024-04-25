package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.*;

import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EnrollmentTest {

    Enrollment enrollment;

    @BeforeEach
    void setUp() {
        enrollment = new Enrollment(1, 1_000);
    }

    @Test
    void 등록_수강인원_예외() {
        enrollment = new Enrollment(1, 1_000);
        Payment payment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), 1_000L);

        assertThatThrownBy(() -> {
            enrollment.enroll(NsUserTest.JAVAJIGI, payment);
            enrollment.enroll(NsUserTest.SANJIGI, payment);
        }).isInstanceOf(CannotRegisterException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, 999L, 1_001L})
    void 등록_수강료_예외(long fee) {
        enrollment = new Enrollment(1, 1_000);
        Payment invalidPayment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), fee);

        assertThatThrownBy(() -> {
            enrollment.enroll(NsUserTest.JAVAJIGI, invalidPayment);
            enrollment.enroll(NsUserTest.SANJIGI, invalidPayment);
        }).isInstanceOf(CannotRegisterException.class);
    }
}
