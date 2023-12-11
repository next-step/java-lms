package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.Course;
import nextstep.courses.domain.course.CourseRepository;
import nextstep.courses.domain.image.ImageRepository;
import nextstep.courses.domain.session.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.courses.infrastructure.TestUtil.autoincrementReset;
import static nextstep.courses.infrastructure.TestUtil.createDefaultTddSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ImageRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        autoincrementReset(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        imageRepository = new JdbcImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);
        Course savedCourse = courseRepository.findById(1L).get();

        final Session tddSession = createDefaultTddSession();
        int count = sessionRepository.save(savedCourse.id(), tddSession);
        assertThat(count).isEqualTo(1);

        Session savedTddSession = sessionRepository.findById(1L).get();

        assertThat(savedTddSession.title()).isEqualTo("tdd");
        assertThat(savedTddSession.sessionStatus()).isEqualTo(SessionStatus.PREPARING);
        assertThat(savedTddSession.amount()).isEqualTo(Amount.of(100L));
    }
}