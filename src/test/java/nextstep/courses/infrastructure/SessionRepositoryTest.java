package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionRepositoryTest {

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
        Session session = createSession();
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getSessionInfo().getTitle()).isEqualTo(savedSession.getSessionInfo().getTitle());

        LOGGER.debug("Session: {}", savedSession);
    }

    private Session createSession() {
        SessionInfo sessionInfo = new SessionInfo(1L,0L, "titl1", "img", SessionType.FREE);
        SessionTimeLine sessionTimeLine = new SessionTimeLine(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        return new Session(sessionInfo, SessionStatus.OPENED, sessionTimeLine, 10L);
    }
}