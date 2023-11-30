package nextstep.courses.infrastructure;

import nextstep.courses.domain.entity.NsCourse;
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
public class NsCourseRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(NsCourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        NsCourse nsCourse = new NsCourse("TDD, 클린 코드 with Java", 1L);
        int count = courseRepository.save(nsCourse);
        assertThat(count).isEqualTo(1);
        NsCourse savedNsCourse = courseRepository.findById(1L);
        assertThat(nsCourse.getTitle()).isEqualTo(savedNsCourse.getTitle());
        LOGGER.debug("Course: {}", savedNsCourse);
    }
}
