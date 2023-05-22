package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.Arrays;
import java.util.List;

import static org.assertj.core.api.Assertions.*;

public class StudentsTest {
    @Test
    void 수강신청_유무() {
        List<Student> students = Arrays.asList(
                new Student(1L, 1L),
                new Student(2L, 1L),
                new Student(3L, 1L));
        Students dut = new Students(students);
        assertThat(dut.enrolledUser(new Student(1L, 1L))).isTrue();
        assertThat(dut.enrolledUser(new Student(4L, 1L))).isFalse();
    }
}
