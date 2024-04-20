package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.infrastructure.engine.CourseRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.BEFORE_EACH_TEST_METHOD)
public class CourseRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("save()")
    void save() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);

        int count = courseRepository.save(course);

        assertThat(count).isEqualTo(1);
    }

    @Test
    @DisplayName("findById() Optional.isPresent()")
    void findByIdIsPresent() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);

        Optional<Course> optionalCourse = courseRepository.findById(1L);

        assertThat(optionalCourse).isPresent();
        assertThat(course.getTitle()).isEqualTo(optionalCourse.get().getTitle());
        LOGGER.debug("Course: {}", optionalCourse);
    }

    @Test
    @DisplayName("findById() Optional.isEmpty()")
    void findByIdIsEmpty() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);

        Optional<Course> optionalCourse = courseRepository.findById(2L);

        assertThat(optionalCourse).isEmpty();
        LOGGER.debug("Course: {}", optionalCourse);
    }
}
