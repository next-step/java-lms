package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static nextstep.courses.domain.SampleUser.*;

@DisplayName("수강신청 객체 테스트")
class EnrollmentTest {

    @DisplayName("수강 신청 객체 생성시 최대 인원을 지정할 수 있다")
    @Test
    void maxEnrollment() {
        Enrollment enrollment = new Enrollment(50);
        Assertions.assertThat(enrollment.maxEnrollmentValue()).isEqualTo(50);
    }
    
    @DisplayName("최대 수강 인원을 초과했는지 확인 할 수 있다")
    @Test
    void isNotExceededMaxEnrollment() {
        Students students = new Students(2);
        Enrollment enrollment = new Enrollment(2);
        students.enroll(JAVAJIGI);
        students.enroll(SANJIGI);
        students.enroll(WOOK);

        boolean exceededMaxEnrollment = enrollment.isNotExceededMaxEnrollment(students);
        Assertions.assertThat(exceededMaxEnrollment).isFalse();
    }
}
