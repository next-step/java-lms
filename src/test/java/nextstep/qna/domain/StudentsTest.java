package nextstep.qna.domain;

import nextstep.courses.domain.Capacity;
import nextstep.courses.domain.Student;
import nextstep.courses.domain.Students;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;
public class StudentsTest {

    @Test
    @DisplayName("같은 id를 가진 학생을 두 번 추가할 수 없다.")
    void cannotRegisteredWithSameId() {
        Students students = new Students();
        students.addStudent(new Student(1L));
        assertThatThrownBy(() -> students.addStudent(new Student(1L)))
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 등록된 학생입니다.");
    }

    @Test
    void checkRegisteredStudents() {
        Students students = new Students();
        students.addStudent(new Student(1L));
        assertTrue(students.contains(new Student(1L)));
        assertFalse(students.contains(new Student(99L)));
    }

    @Test
    @DisplayName("학생을 추가하고 제거할 수 있다.")
    void addAndRemoveStudent() {
        Students students = new Students();

        Student student = new Student(1L);
        students.addStudent(student);
        assertTrue(students.contains(student));

        students.removeStudent(student);
        assertFalse(students.contains(student));
    }
}
