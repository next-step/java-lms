package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class FreeSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private FreeSessionRepository freeSessionRepository;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        courseRepository = new JdbcCourseRepository(jdbcTemplate);
        freeSessionRepository = new JdbcFreeSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Course course = courseRepository.findById(2L);
        FreeSession freeSession = new FreeSession(1L,
                1L,
                course.getId(),
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE,
                LocalDateTime.now(),
                LocalDateTime.now());
        int freeSessionSavedCount = freeSessionRepository.save(freeSession);
        assertThat(freeSessionSavedCount).isEqualTo(1);

        FreeSession savedSession = freeSessionRepository.findById(1L);
        LOGGER.info("savedSession = {}", savedSession);
        LOGGER.info("freeSession = {}", freeSession);

        assertThat(freeSession).isEqualTo(savedSession);
    }
}
