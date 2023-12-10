package nextstep.courses.infrastructure;

import nextstep.courses.domain.session.SelectStatus;
import nextstep.courses.domain.session.SelectionUser;
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
class JdbcSessionSelectionUserListRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(JdbcSessionSelectionUserListRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionSelectionUserListRepository sessionUserListRepository;

    @BeforeEach
    void setUp() {
        sessionUserListRepository = new JdbcSessionSelectionUserListRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        final long userId = 1L;
        final long sessionId = 2L;
        SelectionUser user = new SelectionUser(SelectStatus.UNDECIDED, NsUserTest.JAVAJIGI);
        int count = sessionUserListRepository.save(user, sessionId);
        assertThat(count).isEqualTo(1);

        final List<SelectionUser> nsUsers = sessionUserListRepository.findAllBySessionId(sessionId);
        assertThat(nsUsers).hasSize(1);
        LOGGER.debug("NsUsers: {}", nsUsers);
    }
}
