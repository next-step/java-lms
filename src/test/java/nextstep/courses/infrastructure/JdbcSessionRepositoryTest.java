package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.CoverImageTest.NORMAL_COVER_IMAGE;
import static nextstep.courses.domain.SessionTest.FREE_SESSION;
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
    void session_save_find() {
        Long sessionId = sessionRepository.save(FREE_SESSION);
        assertThat(sessionId).isEqualTo(1L);
        Session savedSession = sessionRepository.findById(sessionId, NORMAL_COVER_IMAGE);
        assertThat(FREE_SESSION.generation()).isEqualTo(savedSession.generation());
        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    void nsUserSession_save_find() {
        NsUserSession nsUserSession = new NsUserSession(1L, 1L);
        int count = sessionRepository.saveNsUserSession(nsUserSession);
        assertThat(count).isEqualTo(1);
        List<NsUserSession> nsUserSessions = sessionRepository.getNsUserSessions(1L);
        assertThat(nsUserSessions).hasSize(1);
    }

}