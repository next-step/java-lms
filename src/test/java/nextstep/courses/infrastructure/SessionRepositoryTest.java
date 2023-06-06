package nextstep.courses.infrastructure;

import nextstep.courses.domain.Session;
import nextstep.users.domain.NsUser;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

import static nextstep.courses.domain.fixture.SessionFixtures.SESSION;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
public class SessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepositoryTest.class);

    @Autowired
    private JdbcTemplate jdbcTemplate;

    private JdbcSessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void session_저장_및_조회가_가능하다() {
        int count = sessionRepository.save(SESSION);
        assertThat(count).isEqualTo(1);
        Session savedSession = sessionRepository.findById(1L);

        assertThat(SESSION.getTitle()).isEqualTo(savedSession.getTitle());
        assertThat(SESSION.getCourseId()).isEqualTo(savedSession.getCourseId());
        assertThat(SESSION.getCoverImage()).isEqualTo(savedSession.getCoverImage());
        assertThat(SESSION.getPeriod()).isEqualTo(savedSession.getPeriod());
        assertThat(SESSION.getType()).isEqualTo(savedSession.getType());
        assertThat(SESSION.getStatus()).isEqualTo(savedSession.getStatus());
        LOGGER.debug("Session: {}", savedSession);
    }

    @Test
    public void 세션에_유저를_추가할_수_있다() throws Exception {
        int count = sessionRepository.saveUser(SESSION.getId(), 1L);
        assertThat(count).isEqualTo(1);

        List<NsUser> allUsers = sessionRepository.findAllUsers(SESSION.getId());
        assertThat(allUsers).hasSize(1);
        assertThat(allUsers.get(0).getId()).isEqualTo(1L);
    }
}
