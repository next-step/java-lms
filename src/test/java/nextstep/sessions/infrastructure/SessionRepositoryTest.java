package nextstep.sessions.infrastructure;

import static nextstep.sessions.testFixture.SessionBuilder.aSession;
import static org.assertj.core.api.AssertionsForClassTypes.assertThat;

import nextstep.sessions.domain.Session;
import nextstep.sessions.domain.SessionRepository;
import nextstep.sessions.domain.SessionRecruitingStatus;
import nextstep.users.domain.UserRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

@JdbcTest
class SessionRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private UserRepository userRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        userRepository = new JdbcUserRepository(jdbcTemplate);
    }

    @Test
    void saveAndFind() {
        Session session = aSession().build();
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findAll().stream().findFirst().orElseThrow();
        assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());
        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void sessionRecruitingStatusUpdateAndFind() {
        Session session = aSession().build();
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);

        Session savedSession = sessionRepository.findAll().stream()
            .filter(s -> s.getTitle().equals(session.getTitle()))
            .findFirst()
            .orElseThrow();
        assertThat(savedSession.getTitle()).isEqualTo(session.getTitle());

        savedSession.recruitStart();
        sessionRepository.update(savedSession);
        Session updatedSession = sessionRepository.findById(savedSession.getId()).orElseThrow();
        assertThat(updatedSession.getRecruitingStatus()).isEqualTo(
            SessionRecruitingStatus.RECRUITING);
    }
}