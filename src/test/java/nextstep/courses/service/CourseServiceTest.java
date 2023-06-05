package nextstep.courses.service;

import nextstep.courses.domain.Course;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
@Transactional
class CourseServiceTest {

    @Autowired
    private CourseService courseService;
    private Course course;

    @BeforeEach
    void setUp() {
        course = new Course(1L, "Course1", 1L, LocalDateTime.now(), LocalDateTime.now());
    }
    @DisplayName("저장")
    void save() {
        long courseId = courseService.save(course);
        assertThat(courseId).isNotNull();
    }

    @DisplayName("조회")
    void findById() {
        long courseId = courseService.save(course);
        Course foundCourse = courseService.findById(courseId);
        assertThat(foundCourse.getId()).isEqualTo(courseId);
    }

}
