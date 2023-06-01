package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionStatus;
import nextstep.courses.domain.SessionUser;
import nextstep.courses.domain.SessionUserRepository;
import nextstep.courses.fixtures.SessionFixtureBuilder;
import nextstep.users.domain.NsUserTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionUserRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionUserRepository sessionUserRepository;

    @BeforeEach
    void setUp() {
        sessionUserRepository = new JdbcSessionUserRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        // given
        Session session = new SessionFixtureBuilder()
                .withStatus(SessionStatus.RECRUITING)
                .build();

        session.enroll(NsUserTest.JAVAJIGI);
        session.enroll(NsUserTest.SANJIGI);

        int count = sessionUserRepository.saveAll(session.getSessionUsers());
        assertThat(count).isEqualTo(2);
        List<SessionUser> savedSessionUsers = sessionUserRepository.findBySession(session.getId());
        assertThat(session.countUsers()).isEqualTo(savedSessionUsers.size());
        LOGGER.debug("SessionUsers: {}", savedSessionUsers);
    }
}
