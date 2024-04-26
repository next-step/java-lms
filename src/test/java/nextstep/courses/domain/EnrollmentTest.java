package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

import nextstep.courses.CannotRegisterException;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.ValueSource;

class EnrollmentTest {

    Enrollment enrollment;

    @BeforeEach
    void setUp() {
        enrollment = Enrollment.createPaidEnrollment(new Students(), 1, 1_000);
    }

    @Test
    void 등록_무료_강의() {
        enrollment.enroll(NsUserTest.JAVAJIGI);
        int expected = 1;

        assertThat(enrollment.countOfEnrolledStudent()).isEqualTo(expected);
    }

    @Test
    void 등록_유로_강의() throws CannotRegisterException {
        Payment payment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), 1_000L);
        int expected = 1;
        enrollment.enroll(NsUserTest.JAVAJIGI, payment);

        assertThat(enrollment.countOfEnrolledStudent()).isEqualTo(expected);
    }


    @Test
    void 등록_수강인원_예외() {
        enrollment = Enrollment.createPaidEnrollment(new Students(), 1, 1_000);
        Payment payment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), 1_000L);

        assertThatThrownBy(() -> {
            enrollment.enroll(NsUserTest.JAVAJIGI, payment);
            enrollment.enroll(NsUserTest.SANJIGI, payment);
        }).isInstanceOf(CannotRegisterException.class);
    }

    @ParameterizedTest
    @ValueSource(longs = {0L, 999L, 1_001L})
    void 등록_수강료_예외(long fee) {
        enrollment = Enrollment.createPaidEnrollment(new Students(), 1, 1_000);
        Payment invalidPayment = new Payment("0", 0L, NsUserTest.JAVAJIGI.getId(), fee);

        assertThatThrownBy(() -> {
            enrollment.enroll(NsUserTest.JAVAJIGI, invalidPayment);
            enrollment.enroll(NsUserTest.SANJIGI, invalidPayment);
        }).isInstanceOf(CannotRegisterException.class);
    }
}
