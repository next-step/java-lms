package nextstep.sessions.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class StudentTest {

    @DisplayName("student객체를 생성한다")
    @Test
    void student() {
        assertThat(new Student(1L, 1L)).isEqualTo(new Student(1L, 1L));
    }
}
