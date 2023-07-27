package nextstep.courses.infrastructure.session;

import nextstep.courses.domain.session.SessionUser;
import nextstep.courses.domain.session.SessionUserRepository;
import nextstep.courses.infrastructure.CourseRepositoryTest;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcOperations;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class SessionUserRepositoryTest {

    private static final Logger LOGGER = LoggerFactory.getLogger(CourseRepositoryTest.class);

    @Autowired
    private JdbcOperations jdbcTemplate;

    private SessionUserRepository sessionUserRepository;

    @BeforeEach
    void setUp() {
        sessionUserRepository = new JdbcSessionUserRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        SessionUser sessionUser1 = new SessionUser(1L, 1L);
        SessionUser sessionUser2 = new SessionUser(1L, 2L);
        int count = sessionUserRepository.save(sessionUser1);
        sessionUserRepository.save(sessionUser2);
        assertThat(count).isEqualTo(1);
        List<SessionUser> users = sessionUserRepository.findBySessionId(1L);
        assertThat(users).hasSize(2);
    }

}
