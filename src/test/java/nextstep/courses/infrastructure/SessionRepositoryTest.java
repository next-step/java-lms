package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setup(){
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = new Session(100L, 100, SessionStatus.RECRUITING.name(), LocalDate.of(2024, 3, 10), LocalDate.of(2024, 4, 10), 1L);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L).get();
        assertThat(savedSession.getEnrollmentManager().getFee()).isEqualTo(100L);
        assertThat(savedSession.getEnrollmentManager().getStatus()).isEqualTo("RECRUITING");
        assertThat(savedSession.getEnrollmentManager().getCount()).isEqualTo(100);
        assertThat(savedSession.getSessionPeriod().getStartDate()).isEqualTo(LocalDate.of(2024, 3, 10));
        assertThat(savedSession.getSessionPeriod().getEndDate()).isEqualTo(LocalDate.of(2024, 4, 10));
        LOGGER.debug("Session : {}", savedSession);
    }
}
