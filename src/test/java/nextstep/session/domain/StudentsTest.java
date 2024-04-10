package nextstep.session.domain;

import nextstep.exception.StudentsException;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;

class StudentsTest {

    @DisplayName("학생을 추가할 수 있다.")
    @Test
    void addStudent() {
        // given
        Students students = new Students();
        NsUser nsUser = new NsUser(1L, "student1", "123", "student1", "student1@naver.com");
        Student enrollStudent = new Student(nsUser);

        // when
        students.add(enrollStudent);

        // then
        assertThat(students.findStudent(enrollStudent)).isPresent();
    }

    @DisplayName("학생을 뺄 수 있다.")
    @Test
    void deleteStudent() {
        // given
        Students students = new Students();
        NsUser nsUser = new NsUser(1L, "student1", "123", "student1", "student1@naver.com");
        Student enrollStudent = new Student(nsUser);
        students.add(enrollStudent);

        // when
        students.remove(enrollStudent);

        // then
        assertThat(students.findStudent(enrollStudent)).isNotPresent();
    }

    @DisplayName("학생은 중복 신청할 수 없다.")
    @Test
    void throwStudentsExceptionForDuplicateEnroll() {
        // given
        Students students = new Students();
        NsUser nsUser = new NsUser(1L, "student1", "123", "student1", "student1@naver.com");
        Student enrollStudent = new Student(nsUser);

        // when
        students.add(enrollStudent);

        // then
        assertThatThrownBy(() -> students.add(enrollStudent))
                .isInstanceOf(StudentsException.class);
    }

    @DisplayName("등록되지 않은 학생을 뺄 수 없다.")
    @Test
    void throwStudentsExceptionRemoveNotEnrolled() {
        // given
        Students students = new Students();
        NsUser nsUser = new NsUser(1L, "student1", "123", "student1", "student1@naver.com");
        NsUser anothreNsUser = new NsUser(2L, "student2", "456", "student2", "student2@naver.com");
        Student enrollStudent = new Student(nsUser);
        Student anotherEnrolNsUser = new Student(anothreNsUser);
        students.add(enrollStudent);

        // then
        assertThatThrownBy(() -> students.remove(anotherEnrolNsUser))
                .isInstanceOf(StudentsException.class);
    }
}
