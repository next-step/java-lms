package nextstep.courses.domain;

import nextstep.courses.ExceedMaxEnrollmentException;
import nextstep.courses.domain.enrollment.Students;
import nextstep.users.domain.NsUser;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.ArrayList;
import java.util.List;

import static nextstep.courses.domain.SampleUser.*;
import static org.assertj.core.api.Assertions.assertThatExceptionOfType;

@DisplayName("학생들 객체 테스트")
class StudentsTest {

    @DisplayName("학생들 객체에 수강생를 추가 할 수 있다")
    @Test
    void addUser() {
        Students students = new Students(3);
        students.enroll(JAVAJIGI);
        students.enroll(SANJIGI);

        Assertions.assertThat(students.countEnrollment()).isEqualTo(2);
        Assertions.assertThat(students.fetchStudents())
                .usingDefaultElementComparator()
                .usingRecursiveFieldByFieldElementComparator()
                .containsExactly(
                        new NsUser(1L, "javajigi", "password", "name", "javajigi@slipp.net", createDate, null),
                        new NsUser(2L, "sanjigi", "password", "name", "sanjigi@slipp.net", createDate, null)
                );
    }

    @DisplayName("학생들 객체를 생성할때 최대 학생수를 초과하면 예외가 발생한다")
    @Test
    void exceedMaxEnrollmentStudent() {
        List<NsUser> students = new ArrayList<>();
        students.add(JAVAJIGI);
        students.add(SANJIGI);
        students.add(WOOK);
        assertThatExceptionOfType(ExceedMaxEnrollmentException.class)
                .isThrownBy(() -> new Students(2, students))
                .withMessage("can not exceed the maximum enrollment");
    }

    @DisplayName("학생을 추가할때 최대 학생수를 초과하면 예외가 발생한다")
    @Test
    void exceedMaxEnrollmentStudentWhenAddStudent() {
        Students students = new Students(2);
        students.enroll(JAVAJIGI);
        students.enroll(SANJIGI);
        assertThatExceptionOfType(ExceedMaxEnrollmentException.class)
                .isThrownBy(() -> students.enroll(WOOK))
                .withMessage("can not exceed the maximum enrollment");
    }
}
