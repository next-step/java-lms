package nextstep.courses.course.domain;

import static org.assertj.core.api.Assertions.assertThat;

import nextstep.courses.course.domain.enumaration.CourseChargeType;
import org.junit.jupiter.api.Test;

class CourseTest {

    @Test
    void 과정의_수강가능여부를_확인할_수_있다() {
    }

    @Test
    void 유료강의인지_확인할_수_있다() {
        Course course = new Course("TDD, 객체지향 과정", 1L, CourseChargeType.PAID);

        assertThat(
                course.isPaid()
        ).isTrue();
    }

    @Test
    void 무료강의인지_확인할_수_있다() {
        Course course = new Course("TDD, 객체지향 과정", 1L, CourseChargeType.FREE);

        assertThat(
                course.isFree()
        ).isTrue();
    }
}