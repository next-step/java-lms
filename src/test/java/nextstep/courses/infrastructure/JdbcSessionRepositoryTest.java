package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoin;
import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
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
        Session session = aSession().withId(1L).build();
        session.addUser(NsUserTest.JAVAJIGI);
        session.addUser(NsUserTest.SANJIGI);
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getSessionType()).isEqualTo(savedSession.getSessionType());
        LOGGER.debug("Session: {}", savedSession);
        sessionRepository.saveSessionJoin(session);
        List<SessionJoin> sessionJoins = sessionRepository.findAllSessionJoinBySessionId(session.getId());
        assertThat(sessionJoins).hasSize(2);
    }

    @Test
    @DisplayName("session join crud")
    @Disabled("session id로 못찾는 이슈가 있음. auto_increase 롤백이 안됨")
    void test02() {
        Session session = aSession().withId(1L).build();
        session.addUser(NsUserTest.JAVAJIGI);
        session.addUser(NsUserTest.SANJIGI);
        sessionRepository.save(session);

        sessionRepository.saveSessionJoin(session);

        List<SessionJoin> sessionJoins = sessionRepository.findAllSessionJoinBySessionId(1L);
        assertThat(sessionJoins).hasSize(2);
    }
}
