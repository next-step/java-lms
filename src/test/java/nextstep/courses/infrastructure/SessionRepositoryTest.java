package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.type.ImageExtension;
import nextstep.courses.type.MaxRegister;
import nextstep.courses.type.SessionDuration;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private CourseRepository courseRepository;
    private SessionRepository sessionRepository;

    @BeforeEach
    public void beforeEach() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, courseRepository);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long courseId = courseRepository.save(course);
        course = courseRepository.findById(courseId);

        SessionImage coverImage = new SessionImage("/a", 500, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-11T16:56:00", "2023-12-13T12:00:00");
        Session session = Session.createPaidSession(1L, course, coverImage, duration, MaxRegister.of(10), 100);

        long pk = sessionRepository.save(session);
        assertThat(pk).isNotEqualTo(0L);

        Session savedSession = sessionRepository.findById(pk);
        assertThat(session).isEqualTo(savedSession);

        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void embeddableTest() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L);
        long coursePk = courseRepository.save(course);
        course = courseRepository.findById(coursePk);

        SessionImage coverImage = new SessionImage("/a", 500, 300, 200, ImageExtension.JPG);
        SessionDuration duration = SessionDuration.fromIso8601("2023-12-11T16:56:00", "2023-12-13T12:00:00");
        Session session = Session.createPaidSession(1L, course, coverImage, duration, MaxRegister.of(10), 100);

        long sessionKey = sessionRepository.save(session);

        Session foundedSession = sessionRepository.findById(sessionKey);

        assertThat(foundedSession.getCoverImage().getSize().getWidth()).isEqualTo(300);
    }
}
