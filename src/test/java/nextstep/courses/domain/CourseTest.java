package nextstep.courses.domain;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    @DisplayName("과정 생성")
    void create() {
        assertThat(new Course()).isInstanceOf(Course.class);
    }
}
