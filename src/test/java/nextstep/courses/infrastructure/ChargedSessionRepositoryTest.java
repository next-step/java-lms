package nextstep.courses.infrastructure;

import nextstep.courses.domain.ChargedSession;
import nextstep.courses.domain.Course;
import nextstep.courses.domain.Duration;
import nextstep.courses.domain.Image;
import nextstep.courses.domain.type.SessionStatus;
import nextstep.courses.repository.ChargedSessionRepository;
import nextstep.courses.repository.CourseRepository;
import nextstep.courses.repository.ImageRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class ChargedSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(ChargedSessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private ChargedSessionRepository chargedSessionRepository;
    private CourseRepository courseRepository;
    private ImageRepository imageRepository;

    @BeforeEach
    void setUp() {
        imageRepository = new JdbcImageRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        chargedSessionRepository = new JdbcChargedSessionRepository(jdbcTemplate);
    }

    @Test
    void create() {
        ChargedSession session = session(savedImage());
        int count = chargedSessionRepository.save(6L, session, savedCourse());
        assertThat(count).isEqualTo(1);
    }

    @Test
    void read() {
        ChargedSession session = chargedSessionRepository.findById(2L);
        assertThat(session).isEqualTo(savedSession());
        LOGGER.debug("ChargedSession: {}", session);
    }

    private ChargedSession session(Image image) {
        return new ChargedSession(1L, duration(), image, SessionStatus.RECRUITING, 0, BigDecimal.valueOf(10_000), LocalDateTime.now(), null);
    }

    private ChargedSession savedSession() {
        return new ChargedSession(2L, duration(), savedImage(), SessionStatus.RECRUITING, 100, BigDecimal.valueOf(1_0000), LocalDateTime.now(), null);
    }

    private Course savedCourse() {
        return courseRepository.findById(2L);
    }

    private Image savedImage() {
        return imageRepository.findById(2L);
    }

    private Duration duration() {
        return new Duration(LocalDate.of(2023, 11, 1), LocalDate.of(2024, 1, 1));
    }

}
