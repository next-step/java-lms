package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.CoverImage;
import nextstep.courses.domain.session.DateRange;
import nextstep.courses.domain.session.Status;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionRepository sessionRepository;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);

        courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));
    }
    @Test
    void free_crud() {
        Course course = courseRepository.findById(1L);
        FreeSession freeSession = new FreeSession(1L,
                course.getId(),
                new DateRange(START, END),
                new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200),
                Status.PREPARE,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());
        int freeSessionSavedCount = sessionRepository.save(freeSession);
        assertThat(freeSessionSavedCount).isEqualTo(1);

        FreeSession savedSession = sessionRepository.findFreeById(1L);
        LOGGER.info("savedSession = {}", savedSession);
        LOGGER.info("freeSession = {}", freeSession);

        assertThat(freeSession).isEqualTo(savedSession);
    }

    @Test
    void paid_crud() {
        Course course = courseRepository.findById(1L);
        PaidSession paidSession = new PaidSession(1L,
                course.getId(),
                new DateRange(START, END),
                new CoverImage(SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200),
                Status.PREPARE,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());
        int paidSessionSavedCount = sessionRepository.save(paidSession);
        assertThat(paidSessionSavedCount).isEqualTo(1);

        PaidSession savedSession = sessionRepository.findPaidById(1L);
        LOGGER.info("savedSession = {}", savedSession);
        LOGGER.info("paidSession = {}", paidSession);

        assertThat(paidSession).isEqualTo(savedSession);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from course");
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("delete from session");
        jdbcTemplate.execute("ALTER TABLE session ALTER COLUMN id RESTART WITH 1");
    }
}
