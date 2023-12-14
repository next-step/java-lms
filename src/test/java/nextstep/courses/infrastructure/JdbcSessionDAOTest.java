package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.courses.domain.session.Session;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.SessionTest.FREE_SESSION;
import static nextstep.users.domain.TeacherTest.NS_USER_SESSION_1;
import static nextstep.users.domain.TeacherTest.NS_USER_SESSION_2;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionDAOTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionDAO.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;
    private SessionDAO sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionDAO(jdbcTemplate);
    }

    @Test
    void session_save_find() {
        Long sessionId = sessionRepository.save(FREE_SESSION);
        assertThat(sessionId).isEqualTo(1L);
        Session savedSession = sessionRepository.findById(sessionId);
        assertThat(FREE_SESSION.generation()).isEqualTo(savedSession.generation());
        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void nsUserSession_save_find() {
        int count = sessionRepository.saveNsUserSession(NS_USER_SESSION_1);
        assertThat(count).isEqualTo(1);
        List<NsUserSession> nsUserSessions = sessionRepository.findNsUserSessionsBySessionId(1L);
        assertThat(nsUserSessions.get(0)).isEqualTo(NS_USER_SESSION_1);

        NsUserSession NS_USER_SESSION_1_TRUE = new NsUserSession(1L, 1L, true);
        sessionRepository.updateNsUserSession(NS_USER_SESSION_1_TRUE);
        List<NsUserSession> updatedNsUserSessions = sessionRepository.findNsUserSessionsBySessionId(1L);
        assertThat(updatedNsUserSessions.get(0)).isEqualTo(NS_USER_SESSION_1_TRUE);
    }
}
