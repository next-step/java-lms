package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.registration.SessionRegistration;
import nextstep.courses.domain.registration.SessionStatus;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.platform.commons.logging.Logger;
import org.junit.platform.commons.logging.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
    @DisplayName("create 와 read 기능을 올바르게 수행할 수 있다.")
    void dbTest_Create_Read() {
        Session session = createSession();
        int count = sessionRepository.save(session);
        Assertions.assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findById(1L);
        Assertions.assertThat(session.getTitle()).isEqualTo(savedSession.getTitle());
    }

    private Session createSession() {
        SessionInfo sessionInfo = new SessionInfo(1L, 0L, "title1", "img", SessionType.FREE);
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        SessionRegistration sessionRegistration = new SessionRegistration(SessionStatus.OPENED, 10L);
        return new Session(sessionInfo, sessionRegistration, sessionPeriod);
    }
}
