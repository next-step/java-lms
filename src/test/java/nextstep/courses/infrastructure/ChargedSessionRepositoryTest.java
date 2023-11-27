package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
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
        chargedSessionRepository = new JdbcChargedSessionRepository(jdbcTemplate, imageRepository);
    }

    @Test
    void crud() {
        ChargedSession session = session(savedImage());
        int count = chargedSessionRepository.save(session, savedCourse());
        assertThat(count).isEqualTo(1);
        ChargedSession savedChargedSession = chargedSessionRepository.findById(1L);
        assertThat(session).isEqualTo(savedChargedSession);
        LOGGER.debug("ChargedSession: {}", savedChargedSession);
    }

    private Course savedCourse() {
        Course course = new Course(1L, "TDD, 클린 코드 with Java", 1L, LocalDateTime.now(), null);
        courseRepository.save(course);
        return course;
    }

    private Image savedImage() {
        Image image = new Image(1L, 1, "JPG", 300, 200, LocalDateTime.now(), null);
        imageRepository.save(image);
        return image;
    }

    private ChargedSession session(Image image) {
        return new ChargedSession(1L, duration(), image, SessionStatus.RECRUITING, 0, BigDecimal.valueOf(10_000), LocalDateTime.now(), null);
    }

    private Duration duration() {
        return new Duration(LocalDate.now(), LocalDate.now());
    }

}
