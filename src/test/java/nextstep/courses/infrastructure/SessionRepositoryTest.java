package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;
import java.time.LocalDateTime;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = new Session(1, "test session", new SessionPeriod(SessionPeriodTest.DATE_230601, SessionPeriodTest.DATE_230630), ChargeType.FREE, new ImageUrl("https://avatars.githubusercontent.com/u/102819554?s=48&v=4"), SessionStatusType.RECRUITING, new SessionStudents(10), 1L, LocalDateTime.now(), null);
        int count = sessionRepository.save(session, 1L);
        assertThat(count).isEqualTo(1);

        Session imgNullSession = new Session(2, "test session2", new SessionPeriod(SessionPeriodTest.DATE_230601, SessionPeriodTest.DATE_230630), ChargeType.FREE, null, SessionStatusType.RECRUITING, new SessionStudents(10), 1L, LocalDateTime.now(), null);
        count = sessionRepository.save(imgNullSession, 1L);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1);
        assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
        LOGGER.debug("Session: {}", savedSession);
    }
}
