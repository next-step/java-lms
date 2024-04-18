package nextstep.courses.domain;

import nextstep.courses.domain.sessionPolicy.FreeSessionPolicyStrategy;
import nextstep.courses.domain.sessionPolicy.PaidSessionPolicyStrategy;
import nextstep.payments.domain.Payment;
import nextstep.users.domain.UserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class EnrollmentTest {

    @DisplayName("성공 - 수강 신청 테스트")
    @Test
    void create_enrollment_test() {
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, new FreeSessionPolicyStrategy());
        enrollment.enroll(UserTest.JAVAJIGI, new Payment());
    }

    @DisplayName("실패 - 수강 신청 상태가 아닐때 테스트")
    @Test
    void not_enrollment_test() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Enrollment(SessionStatus.PREPARING, new FreeSessionPolicyStrategy())
                        .enroll(UserTest.JAVAJIGI, new Payment()));
    }

    @DisplayName("수강인원 초과 테스트")
    @Test
    void exceed_capacity_test() {
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING,
                new PaidSessionPolicyStrategy(1, 0L));
        Payment payment = new Payment("payment_id_1", 2L, 2L, 0L);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(UserTest.JAVAJIGI, payment);
            enrollment.enroll(UserTest.SANJIGI, payment);
        });
    }

    @DisplayName("수강인원 중복 테스트")
    @Test
    void duplication_student_test() {
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING,
                new PaidSessionPolicyStrategy(3, 0L));
        Payment payment = new Payment("payment_id_1", 2L, 2L, 0L);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(UserTest.JAVAJIGI, payment);
            enrollment.enroll(UserTest.JAVAJIGI, payment);
        });
    }
}