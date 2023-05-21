package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoin;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    @DisplayName("session crud")
    void test01() {
        Session session = aSession().build();
        long sessionId = sessionRepository.save(session);
        assertThat(sessionId).isGreaterThan(0);
    }

    @Test
    @DisplayName("session 조회")
    void test02() {
        Session session = aSession().build();
        long sessionId = sessionRepository.save(session);

        Session findSession = sessionRepository.findById(sessionId);
        assertThat(sessionId).isEqualTo(findSession.getId());
        assertThat(session.getSessionType()).isEqualTo(findSession.getSessionType());
    }

    @Test
    @DisplayName("session join crud")
    void test03() {
        Session session = aSession().build();
        long sessionId = sessionRepository.save(session);
        Session savedSession = aSession().withId(sessionId).build();
        savedSession.addUser(NsUserTest.JAVAJIGI);
        savedSession.addUser(NsUserTest.SANJIGI);

        sessionRepository.saveSessionJoin(savedSession);

        List<SessionJoin> sessionJoins = sessionRepository.findAllSessionJoinBySessionId(savedSession.getId());
        assertThat(sessionJoins).hasSize(2);
    }
}
