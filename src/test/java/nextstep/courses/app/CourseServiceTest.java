package nextstep.courses.app;

import nextstep.courses.domain.Course;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static nextstep.fixtures.CourseFixtures.testCourse1;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @DisplayName("저장 후 조회")
    @Test
    void saveSelect() {
        courseService.save(testCourse1());

        Course findCourse = courseService.findById(1L);

        assertThat(findCourse.getId()).isEqualTo(1L);
    }
}

