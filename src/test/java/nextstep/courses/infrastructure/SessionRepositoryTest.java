package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionRepository;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.fixtures.SessionFixtureBuilder;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        // given
        Session session = new SessionFixtureBuilder()
                .withId(1L)
                .withStatus(SessionStatus.RECRUITING)
                .build();

        int count = sessionRepository.save(session);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(session.getId());
        assertThat(session.getId()).isEqualTo(savedSession.getId());
        LOGGER.debug("Session: {}", savedSession);
    }
}
