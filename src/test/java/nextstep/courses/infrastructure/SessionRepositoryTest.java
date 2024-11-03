package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
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
import java.util.List;

import static nextstep.courses.domain.CoverImageTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.ProcessStatus.READY;
import static nextstep.courses.domain.RecruitmentStatus.CLOSED;
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
    private long sessionId;
    private Course course;
    private CoverImage coverImage;
    private Status prepare;
    private List<CoverImage> coverImages;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    @BeforeEach
    void setUp() {
        sessionId = 1L;
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);

        courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));

        course = courseRepository.findById(sessionId);
        coverImage = new CoverImage(sessionId, SIZE_1024, IMAGE_TYPE_TEXT_GIF, WIDTH_300, HEIGHT_200);
        prepare = Status.PREPARE;
        coverImages = List.of();
        createdAt = LocalDateTime.of(2024, 10, 26, 10, 0);
        updatedAt = LocalDateTime.of(2024, 11, 26, 10, 0);
    }
    @Test
    void free_crud() {
        FreeSession freeSession = new FreeSession(sessionId,
                course.getId(), new DateRange(START, END),
                coverImage, prepare,
                coverImages, IN1, READY, CLOSED,
                1L, createdAt, updatedAt);
        int freeSessionSavedCount = sessionRepository.save(freeSession);
        assertThat(freeSessionSavedCount).isEqualTo(1);

        FreeSession savedSession = sessionRepository.findFreeById(sessionId);
        LOGGER.info("savedSession = {}", savedSession);
        LOGGER.info("freeSession = {}", freeSession);

        assertThat(freeSession).isEqualTo(savedSession);
    }

    @Test
    void paid_crud() {
        PaidSession paidSession = new PaidSession(sessionId,
                course.getId(), new DateRange(START, END),
                coverImage, prepare,
                coverImages, IN1, READY, CLOSED,
                MAX_REGISTER_COUNT, SESSION_AMOUNT,
                1L, createdAt, updatedAt);
        int paidSessionSavedCount = sessionRepository.save(paidSession);
        assertThat(paidSessionSavedCount).isEqualTo(1);

        PaidSession savedSession = sessionRepository.findPaidById(sessionId);
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
