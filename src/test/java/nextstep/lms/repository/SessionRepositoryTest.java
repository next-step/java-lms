package nextstep.lms.repository;

import nextstep.lms.domain.Course;
import nextstep.lms.domain.LmsUser;
import nextstep.lms.domain.Session;
import nextstep.lms.domain.SessionCoverImg;
import nextstep.lms.infrastructure.JdbcCourseRepository;
import nextstep.lms.infrastructure.JdbcLmsUserRepository;
import nextstep.lms.infrastructure.JdbcSessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.util.Optional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;

@JdbcTest
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;
    private LmsUserRepository lmsUserRepository;

    @BeforeEach
    void setUp() {
        lmsUserRepository = new JdbcLmsUserRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate, lmsUserRepository);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, lmsUserRepository, courseRepository);
    }

    @Test
    void crud() {
        LmsUser user = lmsUserRepository.findByUserId("javajigi");
        Course course = courseRepository.findByTitle("java_course");

        Session session = Session.of("new강의", course, user, 0, 100, new SessionCoverImg(null), LocalDate.of(2023, 6, 1), LocalDate.of(2023, 6, 30));
        int count = sessionRepository.save(session);

        Session savedSession = sessionRepository.findByTitle("new강의");
        assertAll(
                () -> assertThat(count).isEqualTo(1),
                () -> assertThat(savedSession.isCourse(course)).isTrue(),
                () -> assertThat(savedSession.isCreator(user)).isTrue(),
                () -> assertThat(savedSession.isFree()).isTrue()
        );

        LOGGER.debug("Session: {}", savedSession);
    }
}
