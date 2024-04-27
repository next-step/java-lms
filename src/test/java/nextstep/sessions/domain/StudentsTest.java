package nextstep.sessions.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentsTest {
    @Test
    void register() {
        Students students = new Students(3);

        // before register(..)
        assertThat(students.countOfStudents()).isEqualTo(0);

        students.register(new Student(1L, 1L));

        // after register(..)
        assertThat(students.countOfStudents()).isEqualTo(1);
    }
}
