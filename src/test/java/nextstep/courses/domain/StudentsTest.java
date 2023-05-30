package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatIllegalStateException;

class StudentsTest {
    @Test
    public void isGreaterEqualThan_True() {
        Students students = new Students();
        students.add(new Student(111L));
        students.add(new Student(222L));
        assertThat(students.isGreaterEqualThan(2L)).isTrue();
    }

    @Test
    public void isGreaterEqualThan_False() {
        Students students = new Students();
        students.add(new Student(111L));
        students.add(new Student(222L));
        assertThat(students.isGreaterEqualThan(3L)).isFalse();
    }

    @Test
    public void add() {
        Students students = new Students();
        students.add(new Student(111L));
        students.add(new Student(222L));

        assertThat(students.isGreaterEqualThan(3L)).isFalse();

        Student student = new Student(333L);
        students.add(student);

        assertThat(students.isGreaterEqualThan(3L)).isTrue();
    }

    @Test
    public void add_AlreadyRegistered() {
        Students students = new Students();
        Student student = new Student(111L);
        students.add(student);
        assertThatIllegalStateException().isThrownBy(() -> students.add(student));
    }
}
