package nextstep.infrastructure;

import nextstep.sessions.domain.*;
import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
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
        Session session = new Session(1L,
                LocalDateTime.of(2023, 06, 01, 10, 00),
                LocalDateTime.of(2023, 06, 15, 10, 00),
                SessionPaymentType.FREE,
                SessionStatus.PREPARING, 3);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getCourseId()).isEqualTo(savedSession.getCourseId());
        LOGGER.debug("Session: {}", savedSession);
    }

}
