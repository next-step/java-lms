package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;

public class StudentsTest {

    private Student student = new Student(1L, 1L);

    @Test
    void 최대수강인원_초과불가() {
        Students students = new Students();

        students.add(student, 2);
        students.add(student, 2);

        assertThatThrownBy(() -> students.add(student, 2))
                .isInstanceOf(RuntimeException.class)
                .hasMessage("강의는 강의 최대 수강 인원을 초과할 수 없습니다.");
    }
}
