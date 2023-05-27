package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionUser;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.SessionFixture.강의_과정_1;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        Session session = 강의_과정_1();
        long count = sessionRepository.save(session);
        LOGGER.debug("Session count: {}", count);
        assertThat(count).isEqualTo(1);
        session.enrollSession(NsUserTest.JAVAJIGI);
        sessionRepository.saveSessionUser(session);
        List<SessionUser> sessionUsers = sessionRepository.findAllBySessionId(session.getId());
        LOGGER.debug("sessionUsers: {}", sessionUsers);
        assertThat(sessionUsers.size()).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getSessionPayment().getStatus()).isEqualTo(savedSession.getSessionPayment().getStatus());
        LOGGER.debug("Session: {}", savedSession);
    }

}
