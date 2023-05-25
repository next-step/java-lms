package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {
    @Test
    void 생성() {
        assertThat(new Student(1L, 1L)).isEqualTo(new Student(1L, 1L));
    }
}
