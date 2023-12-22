package nextstep.courses.infrastructure;

import nextstep.courses.domain.course.session.Session;
import nextstep.courses.domain.course.session.SessionRepository;
import nextstep.courses.domain.course.session.SessionState;
import nextstep.courses.domain.course.session.SessionType;
import nextstep.courses.domain.course.session.apply.Applies;
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
        Session findSession = sessionRepository.findById(savedSession.getId()).orElseThrow(NotFoundException::new);

        assertThat(findSession.getId()).isEqualTo(1L);
        assertThat(findSession.getDuration()).isEqualTo(session.getDuration());
        assertThat(findSession.getSessionState()).isEqualTo(session.getSessionState());

        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void update_success() {
        Session session = SessionFixtures.createdChargedSession();
        Session savedSession = sessionRepository.save(1L, session);

        SessionState updateSessionState = new SessionState(SessionType.CHARGE, 2000L, 30, new Applies());
        savedSession.changeSessionState(updateSessionState);
        sessionRepository.update(savedSession.getId(), savedSession);
        Session updatedSession = sessionRepository.findById(savedSession.getId()).orElseThrow(NotFoundException::new);

        assertThat(updatedSession.getId()).isEqualTo(savedSession.getId());
        assertThat(updatedSession.getSessionState()).isEqualTo(updateSessionState);
        LOGGER.debug("Session: {}", updatedSession);
    }
}
