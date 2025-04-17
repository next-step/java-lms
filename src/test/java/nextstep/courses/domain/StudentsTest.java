package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThatCode;
import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentsTest {

    @Test
    @DisplayName("학생이 이미 등록되어 있으면 예외가 발생한다.")
    void createStudentsWithAlreadyEnrolled() {
        Students students = new Students(1);
        Student student = new Student();
        students.register(student, 0L);
        assertThrows(IllegalArgumentException.class, () -> students.register(student, 0L));
    }

    @Test
    @DisplayName("학생 수를 초과하면 예외가 발생한다.")
    void createStudentsWithExceedLimit() {
        Students students = new Students(0);
        assertThrows(IllegalArgumentException.class, () -> students.register(new Student(), 0L));
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치할 때 수강 신청이 가능하다.")
    void createPaidSessionWithCorrectPrice() {
        Students students = new Students(1);
        assertThatCode(() -> students.register(new Student(800_000L), 800_000L)).doesNotThrowAnyException();
    }

    @Test
    @DisplayName("유료 강의는 수강생이 결제한 금액과 수강료가 일치하지 않으면 수강 신청이 불가능하다.")
    void createPaidSessionWithNotEnoughPrice() {
        Students students = new Students(1);
        assertThrows(IllegalArgumentException.class, () -> students.register(new Student(790_000L), 800_000L));
    }

}
