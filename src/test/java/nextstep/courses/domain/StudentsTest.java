package nextstep.courses.domain;

import nextstep.courses.ExceedMaxEnrollmentException;
import nextstep.courses.domain.enrollment.Student;
import nextstep.courses.domain.enrollment.Students;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("학생들 객체 테스트")
class StudentsTest {

    List<Student> students;
    Student student1 = new Student(1L, 1L);
    Student student2 = new Student(2L, 1L);
    Student student3 = new Student(3L, 1L);
    @BeforeEach
    void init(){
        students = new ArrayList<>();
    }

    @DisplayName("학생들 객체에 수강생를 추가 할 수 있다")
    @Test
    void addUser() {
        Students students = new Students(3);
        students.enroll(student1);
        students.enroll(student2);

        Assertions.assertThat(students.countEnrollment()).isEqualTo(2);
        Assertions.assertThat(students.fetchStudents())
                .usingDefaultElementComparator()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        new Student(1L, 1L),
                        new Student(2L, 1L)
                );
    }

    @DisplayName("학생들 객체를 생성할때 최대 학생수를 초과하면 예외가 발생한다")
    @Test
    void exceedMaxEnrollmentStudent() {
        List<Student> students = new ArrayList<>();
        students.add(student1);
        students.add(student2);
        students.add(student3);

        assertThatExceptionOfType(ExceedMaxEnrollmentException.class)
                .isThrownBy(() -> new Students(2, students))
                .withMessage("can not exceed the maximum enrollment");
    }

    @DisplayName("학생을 추가할때 최대 학생수를 초과하면 예외가 발생한다")
    @Test
    void exceedMaxEnrollmentStudentWhenAddStudent() {
        Students students = new Students(2);
        students.enroll(student1);
        students.enroll(student2);

        assertThatExceptionOfType(ExceedMaxEnrollmentException.class)
                .isThrownBy(() -> students.enroll(student3))
                .withMessage("can not exceed the maximum enrollment");
    }

    @DisplayName("현재 세션에 등록한 학생의 숫자를 구할수 있다")
    @Test
    void currentEnrolmentCount() {
        Students students = new Students(2);
        students.enroll(student1);
        assertThat(students.countEnrollment()).isEqualTo(1);
    }
}
