package nextstep.lms.repository;

import nextstep.lms.domain.Course;
import nextstep.lms.domain.LmsUser;
import nextstep.lms.infrastructure.JdbcCourseRepository;
import nextstep.lms.infrastructure.JdbcLmsUserRepository;
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
    private LmsUserRepository lmsUserRepository;

    @BeforeEach
    void setUp() {
        lmsUserRepository = new JdbcLmsUserRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate, lmsUserRepository);
    }

    @Test
    void crud() {
        LmsUser user = lmsUserRepository.findByUserId("javajigi");
        Course course = Course.of("new과정", user);
        int count = courseRepository.save(course);
        assertThat(count).isEqualTo(1);
        Course savedCourse = courseRepository.findByTitle("new과정");
        assertThat("new과정").isEqualTo(savedCourse.getTitle());
        LOGGER.debug("Course: {}", savedCourse);
    }
}
