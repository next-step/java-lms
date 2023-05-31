package nextstep.courses.domain;

import nextstep.courses.exception.DuplicateStudentException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThatThrownBy;

public class StudentsTest {

    @Test
    void 학생등록() {
        Students students = new Students();

        students.add(new Student("jerry","제리","jerry@gamil.com"));

        Assertions.assertThat(students.getSize()).isEqualTo(1);
    }

    @Test
    void 중복등록불가테스트() {
        Students students = new Students();

        students.add(new Student("jerry", "제리", "jerry@gamil.com"));

        assertThatThrownBy(() -> {
            students.add(new Student("jerry", "제리2", "jerry2@gamil.com"));
        }).isInstanceOf(DuplicateStudentException.class).hasMessageContaining("이미 등록된 학생ID입니다.");
    }

}
