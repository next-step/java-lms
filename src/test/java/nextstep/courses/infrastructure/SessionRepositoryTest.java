package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUser;
import nextstep.users.domain.NsUserTest;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.Set;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;
    private Session session;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
        session = aSession().build();
    }

    @DisplayName("Session 저장")
    @Test
    void save() {
        long sessionId = sessionRepository.save(session);
        Session savedSession = aSession().withId(sessionId).build();
        assertThat(savedSession.getId()).isEqualTo(sessionId);
    }

    @DisplayName("Session 조회")
    @Test
    void findById() {
        long sessionId = sessionRepository.save(session);

        Session foundSession = sessionRepository.findById(sessionId);
        assertThat(foundSession).isNotNull();
        assertThat(foundSession.getId()).isEqualTo(sessionId);
    }

    @DisplayName("Session User 저장")
    @Test
    void saveSessionUser() {
        long sessionId = sessionRepository.save(session);
        Session foundSession = sessionRepository.findById(sessionId);
        long sessionUsersId = sessionRepository.saveSessionUser(foundSession, NsUserTest.JAVAJIGI);

        assertThat(sessionUsersId).isNotNull();
    }

    @DisplayName("Session UserIds 조회")
    @Test
    void findSessionUserIdsBySessionId() {
        long sessionId = sessionRepository.save(session);
        Session foundSession = sessionRepository.findById(sessionId);

        sessionRepository.saveSessionUser(foundSession, NsUserTest.JAVAJIGI);
        sessionRepository.saveSessionUser(foundSession, NsUserTest.SANJIGI);

        List<Long> userIds = sessionRepository.findSessionUserIdsBySessionId(sessionId);
        assertThat(userIds).hasSize(2);
    }

}
