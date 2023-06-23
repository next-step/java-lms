package nextstep.courses.domain.registration;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @DisplayName("Student 객체 생성")
    @Test
    void 객체_생성() {
        Student student = new Student(1L, 1L);
        assertThat(student).isEqualTo(new Student(1L, 1L));
    }

    @DisplayName("Student 수강 승인")
    @Test
    void 수강_승인() {
        Student student = new Student(1L, 1L, false);
        student.approve();
        assertThat(student).isEqualTo(new Student(1L, 1L, true));
    }

    @DisplayName("Student 수강 취소")
    @Test
    void 수강_취소() {
        Student student = new Student(1L, 1L, true);
        student.reject();
        assertThat(student).isEqualTo(new Student(1L, 1L, false));
    }
}