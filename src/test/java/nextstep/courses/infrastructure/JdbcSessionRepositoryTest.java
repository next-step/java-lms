package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.time.LocalDateTime;
import java.util.List;

import static nextstep.fixtures.SessionFixtures.*;
import static org.assertj.core.api.Assertions.*;

@JdbcTest
public class JdbcSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @DisplayName("Session save")
    @Test
    void save() {
        Session session = testSession1();
        long sessionId = sessionRepository.save(session);
        assertThat(sessionId).isGreaterThan(0);
    }

    @DisplayName("session 조회")
    @Test
    void select() {
        Session session = testSession1();
        long sessionId = sessionRepository.save(session);

        Session findSession = sessionRepository.findById(sessionId);
        assertThat(sessionId).isEqualTo(findSession.getId());
        assertThat(session.getSessionStatus()).isEqualTo(findSession.getSessionStatus());
    }
}
