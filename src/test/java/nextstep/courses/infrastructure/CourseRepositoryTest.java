package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.CourseTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
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
    void crud() {
        int count = courseRepository.save(CourseTest.C1);
        assertThat(count).isEqualTo(1);
        Course savedCourse = courseRepository.findById(CourseTest.C1.getId());
        assertThat(CourseTest.C1.getTitle()).isEqualTo(savedCourse.getTitle());
        LOGGER.debug("Course: {}", savedCourse);
    }
}
