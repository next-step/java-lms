package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.*;
import nextstep.courses.fixture.SessionFixtures;
import nextstep.qna.NotFoundException;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

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
    void save_success() {
        Session session = SessionFixtures.createdFreeSession();
        Session savedSession = sessionRepository.save(1L, session);
        Session findSession = sessionRepository.findById(savedSession.id()).orElseThrow(NotFoundException::new);

        assertThat(findSession.id()).isEqualTo(1L);
        assertThat(findSession.sessionDuration()).isEqualTo(session.sessionDuration());
        assertThat(findSession.sessionState()).isEqualTo(session.sessionState());

        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void update_success() {
        Session session = SessionFixtures.createdChargedSession();
        Session savedSession = sessionRepository.save(1L, session);

        SessionState updateSessionState = new SessionState(SessionType.CHARGE, 2000L, 30);
        Session updatedSession = new Session(savedSession.images(), savedSession.sessionDuration(),
                updateSessionState, savedSession.creatorId(), savedSession.createdAt());
        sessionRepository.update(savedSession.id(), updatedSession);
        Session findUpdatedSession = sessionRepository.findById(savedSession.id()).orElseThrow(NotFoundException::new);

        assertThat(findUpdatedSession.id()).isEqualTo(savedSession.id());
        assertThat(findUpdatedSession.sessionState()).isEqualTo(updateSessionState);
        LOGGER.debug("Session: {}", findUpdatedSession);
    }
}
