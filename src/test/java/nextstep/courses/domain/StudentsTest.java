package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentsTest {

    @Test
    @DisplayName("수강생 수를 올릴 수 있다")
    void add() {
        Students students = new Students(0);

        Students actual = students.add();
        Students expected = new Students(1);

        assertThat(actual).isEqualTo(expected);
    }
}
