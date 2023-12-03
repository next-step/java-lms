package nextstep.courses.infrastructure;

import nextstep.users.domain.NsUser;
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
class JdbcSessionUserListRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSessionUserListRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionUserListRepository sessionUserListRepository;

    @BeforeEach
    void setUp() {
        sessionUserListRepository = new JdbcSessionUserListRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        final long userId = 1L;
        final long sessionId = 2L;
        int count = sessionUserListRepository.save(userId, sessionId);
        assertThat(count).isEqualTo(1);

        final List<NsUser> nsUsers = sessionUserListRepository.findAllBySessionId(sessionId);
        assertThat(nsUsers).hasSize(1);
        LOGGER.debug("NsUsers: {}", nsUsers);
    }
}
