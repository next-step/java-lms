package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.Assertions.*;


class CourseTest {

    @DisplayName("과정은 기수 단위로 운영")
    @Test
    void 과정_기수() {
        Course course = new Course("Course Test", 10000L, 1);
        int ordinal = course.getOrdinal();

        assertThat(ordinal).isEqualTo(1);
    }
}