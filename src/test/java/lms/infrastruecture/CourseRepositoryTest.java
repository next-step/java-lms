package lms.infrastruecture;

import static org.assertj.core.api.Assertions.assertThat;

import lms.domain.Course;
import lms.domain.CourseRepository;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
public class CourseRepositoryTest {
    @Autowired
    private CourseRepository courseRepository;

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        int count = courseRepository.save(course);
        assertThat(count).isEqualTo(1);
        Course savedCourse = courseRepository.findById(1L);
        assertThat(course.getTitle()).isEqualTo(course.getTitle());
    }
}
