package nextstep.courses.service;

import nextstep.courses.domain.Course;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseServiceTest {

    @Autowired
    CourseService courseService;

    @Test
    void save() {
        String title = "title";
        Long creatorId = 1L;
        Course course = new Course(title, creatorId);

        int save = courseService.save(course);
        Assertions.assertThat(save).isEqualTo(1);

        Course findCourse = courseService.findById(1L);
        Assertions.assertThat(findCourse.getTitle()).isEqualTo(title);
        Assertions.assertThat(findCourse.getCreatorId()).isEqualTo(creatorId);

    }
}
