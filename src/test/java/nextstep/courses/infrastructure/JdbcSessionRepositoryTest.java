package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static nextstep.Fixtures.aSession;
import static org.assertj.core.api.Assertions.assertThat;

@SpringBootTest
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
        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getSessionType()).isEqualTo(savedSession.getSessionType());
        LOGGER.debug("Session: {}", savedSession);
    }
}
