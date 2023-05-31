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

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);
    private final DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    private CourseRepository courseRepository;

    private Course course;

    private final LocalDateTime initStartedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
    private final LocalDateTime initEndedAt = LocalDateTime.parse("2022-01-01 11:11:11", formatter);
    private final boolean initIsFree = true;
    private final Status initStatus = Status.PREPARING;
    private final int initCurrentStudents = 0;
    private final int initMaxStudents = 10;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);

        course = new Course("TDD, 클린 코드 with Java", 1L);
        courseRepository.save(course);
    }

    @Test
    @DisplayName("crud")
    void crud() {
        Session session = new Session(initStartedAt, initEndedAt, initIsFree, initStatus, initCurrentStudents, initMaxStudents);
        int count = sessionRepository.save(course.id(), session);
        assertThat(count).isEqualTo(1);


        Session session2 = new Session(initStartedAt, initEndedAt, initIsFree, initStatus, initCurrentStudents, initMaxStudents);
        count += sessionRepository.save(course.id(), session2);
        List<Session> savedSessions = sessionRepository.findByCourseId(course.id());

        int actual = savedSessions.size();
        assertThat(actual).isEqualTo(count);

        course.addSession(session);
        course.addSession(session2);

        LOGGER.debug("Session: ");
        savedSessions.forEach(savedSession -> {
            LOGGER.debug("{}", savedSession);
        });
    }
}
