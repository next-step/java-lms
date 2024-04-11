package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentsTest {
    @Test
    void 생성() {
        List<Student> studentList = List.of(new Student(1L));
        assertThat(new Students(studentList)).isEqualTo(new Students(studentList));
    }
}
