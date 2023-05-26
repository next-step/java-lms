package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;

    private Session session;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        sessionRepository = new JdbcSessionRepository(jdbcTemplate, courseRepository);
        Course course = new Course(1L, "TDD, 클린 코드 with Java", 1L, LocalDateTime.now(), null);
        courseRepository.save(course);

        session = new Session(1L, course, new SessionImage("a", "/"),
                new SessionCapacity(0, 10), "TDD, 클린코드", SessionType.PAY,
                SessionStatus.READY, LocalDateTime.now(), null);
    }

    @Test
    void crud() {
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getName()).isEqualTo(savedSession.getName());
        session.enroll();
        sessionRepository.update(session);
        assertThat(session.getCapacity().getNumber()).isEqualTo(1);
        LOGGER.debug("Session: {}", savedSession);
    }
}
