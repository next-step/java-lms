package nextstep.courses.app;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import static nextstep.Fixtures.aCourse;
import static org.assertj.core.api.Assertions.assertThat;

@Transactional
@SpringBootTest
class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @Test
    @DisplayName("저장 - 조회")
    void test01() {
        courseService.save(aCourse().withId(1L).build());

        Course findCourse = courseService.findById(1L);

        assertThat(findCourse.getId()).isEqualTo(1L);
    }
}
