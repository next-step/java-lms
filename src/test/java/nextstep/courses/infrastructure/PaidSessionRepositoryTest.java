package nextstep.courses.infrastructure;

import nextstep.courses.domain.Course;
import nextstep.courses.domain.CourseTest;
import nextstep.courses.domain.PaidSession;
import nextstep.courses.domain.PaidSessionRepository;
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

import static nextstep.courses.domain.PaidSessionTest.MAX_REGISTER_COUNT;
import static nextstep.courses.domain.PaidSessionTest.SESSION_AMOUNT;
import static nextstep.courses.domain.session.CoverImageTest.*;
import static nextstep.courses.domain.session.DateRangeTest.END;
import static nextstep.courses.domain.session.DateRangeTest.START;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class PaidSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private PaidSessionRepository paidSessionRepository;

    @BeforeEach
    void setUp() {
        paidSessionRepository = new JdbcPaidSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        PaidSession paidSession = new PaidSession(1L,
                1L,
                2L,
                new DateRange(START, END),
                new CoverImage(SIZE, IMAGE_TYPE_TEXT, WIDTH, HEIGHT),
                Status.PREPARE,
                MAX_REGISTER_COUNT,
                SESSION_AMOUNT,
                LocalDateTime.now(),
                LocalDateTime.now());
        int paidSessionSavedCount = paidSessionRepository.save(paidSession);
        assertThat(paidSessionSavedCount).isEqualTo(1);

        PaidSession savedSession = paidSessionRepository.findById(1L);
        LOGGER.info("savedSession = {}", savedSession);
        LOGGER.info("paidSession = {}", paidSession);

        assertThat(paidSession).isEqualTo(savedSession);
    }
}
