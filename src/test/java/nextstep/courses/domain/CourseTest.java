package nextstep.courses.domain;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

public class CourseTest {
    @DisplayName("Course 객체가 잘 생성되는지 확인")
    @Test
    void Course_객체가_정상적으로_생성되는지_확인() {
        assertThat(Course.of("TDD, 클린 코드 with Java", 1L)).isInstanceOf(Course.class);
    }
}
