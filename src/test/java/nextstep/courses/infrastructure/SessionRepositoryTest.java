package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.test.annotation.DirtiesContext;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
@DirtiesContext(classMode = DirtiesContext.ClassMode.AFTER_EACH_TEST_METHOD)
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private CoverImageRepository coverImageRepository;

    @BeforeEach
    void setup() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        coverImageRepository = new JdbcCoverImageRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = new Session(100L, 100, SessionStatus.RECRUITING.name(), LocalDate.of(2024, 3, 10), LocalDate.of(2024, 4, 10), 1L);
        CoverImage coverImage = new CoverImage(9_000L, "GIF", 300L, 200L, 1L, 0L);
        int savedSessionCount = sessionRepository.save(session);
        int savedCoverImageCount = coverImageRepository.save(coverImage);
        assertThat(savedSessionCount).isEqualTo(1);
        assertThat(savedCoverImageCount).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L).get();
        assertThat(savedSession.getEnrollmentManager().getFee()).isEqualTo(100L);
        assertThat(savedSession.getEnrollmentManager().getStatus()).isEqualTo("RECRUITING");
        assertThat(savedSession.getEnrollmentManager().getCount()).isEqualTo(100);
        assertThat(savedSession.getSessionPeriod().getStartDate()).isEqualTo(LocalDate.of(2024, 3, 10));
        assertThat(savedSession.getSessionPeriod().getEndDate()).isEqualTo(LocalDate.of(2024, 4, 10));
        assertThat(savedSession.getCoverImage().getCapacity()).isEqualTo(9_000L);
        assertThat(savedSession.getCoverImage().getType()).isEqualTo("GIF");
        assertThat(savedSession.getCoverImage().getWidth()).isEqualTo(300L);
        assertThat(savedSession.getCoverImage().getHeight()).isEqualTo(200L);
        LOGGER.debug("Session : {}", savedSession);
    }
}
