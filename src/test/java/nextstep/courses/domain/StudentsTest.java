package nextstep.courses.domain;

import nextstep.courses.exception.AlreadyAddStudentException;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.junit.jupiter.api.Assertions.assertThrows;

class StudentsTest {

    @Test
    @DisplayName("이미 신청한 학생은 신청 할 수 없다")
    void add() {
        Students students = new Students(List.of(new Student()));

        assertThrows(AlreadyAddStudentException.class, () -> students.enroll(new Student()), "이미 수강을 신청한 학생입니다.");
    }
}
