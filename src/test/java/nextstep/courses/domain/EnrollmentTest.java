package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.Enrollment;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SampleUser.JAVAJIGI;
import static nextstep.courses.domain.SampleUser.WOOK;
import static org.assertj.core.api.Assertions.*;

@DisplayName("수강신청 객체 테스트")
class EnrollmentTest {

    @DisplayName("수강 신청 객체 생성시 최대 인원을 지정할 수 있다")
    @Test
    void maxEnrollment() {
        Enrollment enrollment = new Enrollment(50);
        assertThat(enrollment.sessionCapacity()).isEqualTo(50);
    }

    @DisplayName("수강 신청을 하면 수강인원 인원이 늘어난다")
    @Test
    void enroll() {
        Enrollment enrollment = new Enrollment(3);

        enrollment.enroll(WOOK);
        enrollment.enroll(JAVAJIGI);

        assertThat(enrollment.hasEnrolledStudent()).isTrue();
        assertThat(enrollment.currentEnrolmentCount()).isEqualTo(2);
    }
}
