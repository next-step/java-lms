package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.HashSet;
import java.util.Set;
import static org.assertj.core.api.Assertions.assertThat;

class StudentsTest {

    @Test
    void create() {
        Set<Student> studentSet = Set.of(new Student(1L, 1L),
                new Student(2L, 2L));
        Students students = new Students(studentSet);

        assertThat(students.getStudents())
                .isEqualTo(studentSet);
    }

    @Test
    void add() {
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(new Student(1L, 1L));
        studentSet.add(new Student(2L, 2L));

        Students students = new Students(studentSet);

        assertThat(students.getStudents())
                .isEqualTo(studentSet);
    }

    @Test
    void size() {
        Set<Student> studentSet = new HashSet<>();
        studentSet.add(new Student(1L, 1L));
        studentSet.add(new Student(2L, 2L));

        Students students = new Students(studentSet);

        assertThat(students.size())
                .isEqualTo(2);

        students.add(new Student(3L, 3L));

        assertThat(students.size())
                .isEqualTo(3);
    }
}