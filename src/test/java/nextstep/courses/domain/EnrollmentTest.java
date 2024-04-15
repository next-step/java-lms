package nextstep.courses.domain;

import nextstep.users.domain.UserTest;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatIllegalArgumentException;

class EnrollmentTest {

    @DisplayName("성공 - 수강 신청 테스트")
    @Test
    void create_enrollment_test(){
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING,50);
        enrollment.enroll(UserTest.JAVAJIGI);
    }

    @DisplayName("실패 - 수강 신청 상태가 아닐때 테스트")
    @Test
    void not_enrollment_test() {
        assertThatIllegalArgumentException().isThrownBy(
                () -> new Enrollment(SessionStatus.PREPARING, 50)
                        .enroll(UserTest.JAVAJIGI));
    }

    @DisplayName("수강인원 초과 테스트")
    @Test
    void exceed_capacity_test() {
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 1);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(UserTest.JAVAJIGI);
            enrollment.enroll(UserTest.SANJIGI);
        });
    }

    @DisplayName("수강인원 중복 테스트")
    @Test
    void duplication_student_test() {
        Enrollment enrollment = new Enrollment(SessionStatus.ENROLLING, 3);
        assertThatIllegalArgumentException().isThrownBy(() -> {
            enrollment.enroll(UserTest.JAVAJIGI);
            enrollment.enroll(UserTest.JAVAJIGI);
        });
    }
}