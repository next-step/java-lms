package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

class StudentTest {
    @Test
    public void equals() {
        assertThat(new Student(2L)).isEqualTo(new Student(2L));
    }
}
