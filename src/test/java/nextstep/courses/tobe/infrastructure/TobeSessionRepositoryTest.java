package nextstep.courses.tobe.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.infrastructure.CourseRepositoryTest;
import nextstep.courses.infrastructure.JdbcCourseRepository;
import nextstep.courses.tobe.domain.*;
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

import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.session.DateRangeTest.*;
import static nextstep.courses.domain.InstructorTest.IN1;
import static nextstep.courses.tobe.domain.ProcessStatus.*;
import static nextstep.courses.tobe.domain.RecruitmentStatus.*;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class TobeSessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;
    private TobeSessionRepository sessionRepository;
    private CourseRepository courseRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new TobeJdbcSessionRepository(jdbcTemplate);
        courseRepository = new JdbcCourseRepository(jdbcTemplate);

        courseRepository.save(new Course("TDD, 클린 코드 with Java", 1L));
    }
    @Test
    void free_crud() {
        Course course = courseRepository.findById(1L);
        TobeFreeSession freeSession = new TobeFreeSession(1L,
                course.getId(),
                DATE_RANGE1,
                List.of(),
                IN1,
                PROCESS,
                OPEN,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());
        int freeSessionSavedCount = sessionRepository.save(freeSession);
        assertThat(freeSessionSavedCount).isEqualTo(1);

        TobeFreeSession savedSession = sessionRepository.findFreeById(1L);
        LOGGER.info("savedSession = {}", savedSession);
        LOGGER.info("freeSession = {}", freeSession);

        assertThat(freeSession).isEqualTo(savedSession);
    }

    @Test
    void paid_crud() {
        Course course = courseRepository.findById(1L);
        TobePaidSession paidSession = new TobePaidSession(1L,
                course.getId(),
                DATE_RANGE1,
                List.of(),
                IN1,
                PROCESS,
                OPEN,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT,
                1L,
                LocalDateTime.now(),
                LocalDateTime.now());
        int paidSessionSavedCount = sessionRepository.save(paidSession);
        assertThat(paidSessionSavedCount).isEqualTo(1);

        TobePaidSession savedSession = sessionRepository.findPaidById(1L);
        LOGGER.info("savedSession = {}", savedSession);
        LOGGER.info("paidSession = {}", paidSession);

        assertThat(paidSession).isEqualTo(savedSession);
    }

    @AfterEach
    void tearDown() {
        jdbcTemplate.execute("delete from course");
        jdbcTemplate.execute("ALTER TABLE course ALTER COLUMN id RESTART WITH 1");
        jdbcTemplate.execute("delete from tobe_session");
        jdbcTemplate.execute("ALTER TABLE tobe_session ALTER COLUMN id RESTART WITH 1");
    }
}
