package nextstep.courses.service;

import nextstep.courses.domain.Course;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
public class CourseServiceTest {
    @Autowired
    private CourseService courseService;

    @Test
    void save() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long courseId = courseService.save(course);
        Course findCourse = courseService.findById(courseId);
        assertThat(findCourse.getTitle()).isEqualTo(course.getTitle());
        assertThat(findCourse.getCreatorId()).isEqualTo(course.getCreatorId());
    }
}
