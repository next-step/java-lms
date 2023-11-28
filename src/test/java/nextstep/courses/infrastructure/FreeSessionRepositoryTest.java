package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.Duration;
import nextstep.courses.domain.FreeSession;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.FreeSessionRepository;
import nextstep.courses.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class FreeSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(FreeSessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FreeSessionRepository freeSessionRepository;
    private CourseRepository courseRepository;
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
    }

    @Test
    void create() {
        FreeSession session = session(savedImage());
        int count = freeSessionRepository.save(5L, session, savedCourse());
        assertThat(count).isEqualTo(1);
    }

    @Test
    void read() {
        FreeSession session = freeSessionRepository.findById(3L);
        assertThat(session).isEqualTo(savedSession());
        LOGGER.debug("FreeSession: {}", session);
    }

    private FreeSession session(Image image) {
        return new FreeSession(1L, duration(), image, SessionStatus.RECRUITING, LocalDateTime.now(), null);
    }

    private FreeSession savedSession() {
        return new FreeSession(3L, duration(), savedImage(), SessionStatus.RECRUITING, LocalDateTime.now(), null);
    }

    private Image savedImage() {
        return imageRepository.findById(2L);
    }

    private Course savedCourse() {
        return courseRepository.findById(2L);
    }

    private Duration duration() {
        return new Duration(LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 1));
    }

}
