package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {


    @Test
    void 학생() {
        Student student = new Student(1L, 1L);

        assertThat(student.getSessionId()).isEqualTo(1L);
        assertThat(student.getUserId()).isEqualTo(1L);
    }
}
