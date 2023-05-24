package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionJoinRepository;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionJoinRepository sessionJoinRepository;

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
        assertThat(session.getSessionBillType()).isEqualTo(findSession.getSessionBillType());
    }
}
