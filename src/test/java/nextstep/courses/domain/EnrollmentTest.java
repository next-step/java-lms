package nextstep.courses.domain;

import nextstep.courses.domain.enrollment.Enrollment;
import nextstep.courses.domain.enrollment.Student;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

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
        List<Student> students = new ArrayList<>();
        students.add(new Student(1L, 1L));
        Enrollment enrollment = new Enrollment(4);
        Student student1 = new Student(2L, 1L);
        Student student2 = new Student(3L, 1L);

        enrollment.enroll(student1, students);
        enrollment.enroll(student2, students);

        assertThat(enrollment.hasEnrolledStudent()).isTrue();
        assertThat(enrollment.currentEnrolmentCount()).isEqualTo(3);
    }
}
