package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@Transactional
public class CourseRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;
    private FreeSessionRepository freeSessionRepository;
    private PaySessionRepository paySessionRepository;

    private Long courseId = 1L;

    @BeforeEach
    void setUp() {
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
        paySessionRepository = new JdbcPaySessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate, freeSessionRepository, paySessionRepository);
    }

    @Test
    @DisplayName("과정/강의 DB에 넣고 조회 테스트")
    void crud() {
        Course course = new Course(courseId, "TDD, 클린 코드 with Java", 1L, LocalDateTime.now(), LocalDateTime.now());
        course.addSession(FreeSessionTest.F1);
        course.addSession(PaySessionTest.P1);

        int count = courseRepository.save(course);
        assertThat(count).isEqualTo(1);

        Course savedCourse = courseRepository.findById(courseId);
        assertThat(course.getTitle()).isEqualTo(savedCourse.getTitle());
        assertThat(course.getSessions().getSessions()).hasSize(2)
                        .contains(FreeSessionTest.F1, PaySessionTest.P1);

        LOGGER.debug("Course: {}", savedCourse);
    }
}
