package nextstep.courses.infrastructure;

import nextstep.courses.domain.model.Course;
import nextstep.courses.domain.repository.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class CourseRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", false, 1L);
        long id = courseRepository.save(course);
        assertThat(id).isEqualTo(course.getId());

        Course savedCourse = courseRepository.findById(id);
        assertThat(course.hasSameTitle(savedCourse)).isTrue();
        assertThat(course.hasSameSelection(savedCourse)).isTrue();
        LOGGER.debug("Course: {}", savedCourse);
    }
}
