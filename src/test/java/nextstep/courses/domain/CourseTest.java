package nextstep.courses.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

public class CourseTest {

    @Test
    @DisplayName("과정은 기수 값을 가지고 있다.")
    void courseHasGeneration() {
        var course = new Course(1);
        Assertions.assertThat(course.getGeneration().getValue()).isEqualTo(1);
    }

}
