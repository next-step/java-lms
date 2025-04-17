package nextstep.courses.domain;

import org.junit.jupiter.api.Test;

import java.math.BigDecimal;

import static org.assertj.core.api.Assertions.*;

class CourseTest {

    Course course = new Course();

    @Test
    void 기수를_가진다() {
        assertThat(course.getClassNo()).isNotNull();
    }

    @Test
    void 여러개의_강의를_가진다() {
        assertThat(course.getSessions()).isNotNull();
    }
}
