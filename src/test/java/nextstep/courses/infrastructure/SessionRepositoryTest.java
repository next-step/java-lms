package nextstep.courses.infrastructure;


import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseRepository;
import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.*;

@JdbcTest
public class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepository.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = new Course("TDD, 클린 코드 with Java", 1L, 1);
        courseRepository.save(course);
        Session session = new Session(LocalDate.now(), LocalDate.now().plusDays(30), 1L);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getName()).isEqualTo(savedSession.getName());
        LOGGER.debug("Session: {}", savedSession);
    }
}
