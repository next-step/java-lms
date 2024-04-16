package nextstep.courses.infrastructure;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;

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
        final Course course = new Course("TDD, 클린 코드 with Java", 1L);
        final int count = courseRepository.save(course);
        assertThat(count).isEqualTo(1);

        final Course savedCourse = courseRepository.findById(1L).get();
        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());

        LOGGER.debug("Course: {}", savedCourse);
    }
}
