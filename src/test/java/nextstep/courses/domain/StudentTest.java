package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {

    @Test
    @DisplayName("수강신청 후 선발되면 강의 참여 상태가 된다")
    void enrollTest() {
        // given
        Student student = new Student(1L, 1L, 1L, Student.StudentStatus.REQUESTED);

        // when
        student.applyEnroll();

        // then
        assertThat(student.getStatus()).isEqualTo(Student.StudentStatus.ENROLLED);
    }

    @Test
    @DisplayName("선발되지 않은 인원은 취소할 수 있다")
    void cancelTest() {
        // given
        Student student = new Student(1L, 1L, 1L, Student.StudentStatus.REQUESTED);

        // when
        student.cancelEnroll();

        // then
        assertThat(student.getStatus()).isEqualTo(Student.StudentStatus.CANCELED);
    }

    @Test
    @DisplayName("선발된 인원은 취소할 수 없다")
    void cancelTest2() {
        // given
        Student student = new Student(1L, 1L, 1L, Student.StudentStatus.ENROLLED);

        // when
        Assertions.assertThatThrownBy(student::cancelEnroll)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강신청이 완료된 학생은 취소할 수 없습니다.");
    }

    @Test
    @DisplayName("이미 취소된 인원은 취소할 수 없다")
    void cancelTest3() {
        // given
        Student student = new Student(1L, 1L, 1L, Student.StudentStatus.CANCELED);

        // when
        Assertions.assertThatThrownBy(student::cancelEnroll)
                .isInstanceOf(IllegalArgumentException.class)
                .hasMessage("이미 수강신청이 취소된 학생은 취소할 수 없습니다.");
    }
}