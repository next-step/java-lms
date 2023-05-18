package nextstep.courses.app;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static nextstep.Fixtures.aCourse;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
class CourseServiceTest {
    @Autowired
    private CourseService courseService;
    @Autowired
    private CourseRepository courseRepository;

    @Test
    @DisplayName("저장")
    void test01() {
        courseService.save(aCourse().build());

        Course findCourse = courseRepository.findById(1L);

        assertThat(findCourse.getId()).isNotNull();
    }

    @Test
    @DisplayName("조회")
    void test02() {
        courseRepository.save(aCourse().build());

        Course findCourse = courseService.findById(1L);

        assertThat(findCourse.getId()).isEqualTo(1L);
    }

}
