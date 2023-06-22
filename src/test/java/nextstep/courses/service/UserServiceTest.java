package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionUser;
import nextstep.courses.domain.Sessions;
import nextstep.courses.infrastructure.JdbcSessionRepository;
import nextstep.courses.infrastructure.JdbcSessionUserMappingRepository;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;

import java.time.LocalDateTime;

import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class UserServiceTest {
    private UserService userService;
    @Autowired
    private JdbcTemplate jdbcTemplate;
    @Autowired
    private NamedParameterJdbcTemplate namedParameterJdbcTemplate;

    @BeforeEach
    public void setup() {
        JdbcSessionUserMappingRepository sessionUserMappingRepository = new JdbcSessionUserMappingRepository(jdbcTemplate, namedParameterJdbcTemplate);
        JdbcSessionRepository sessionRepository = new JdbcSessionRepository(jdbcTemplate, namedParameterJdbcTemplate);
        JdbcUserRepository userRepository = new JdbcUserRepository(jdbcTemplate, namedParameterJdbcTemplate);
        userService = new UserService(sessionUserMappingRepository, sessionRepository, userRepository);
    }

    @Test
    void registerSession() {
        SessionUser sessionUser = new SessionUser(66L, 10L, 20L, LocalDateTime.now());
        userService.registerSession(sessionUser);

        Sessions sessions = userService.findSessionUser(sessionUser.getNsUserId());
        assertThat(sessions.getSize()).isEqualTo(1);
    }

    @Test
    void removeSession() {
        SessionUser sessionUser = new SessionUser(1001L, 10L, 1L, LocalDateTime.now());
        int removeCount = userService.removeSession(sessionUser);
        assertThat(removeCount).isEqualTo(1);
    }

    @Test
    void findSession() {
        Sessions sessionUser = userService.findSessionUser(2L);
        assertThat(sessionUser.getSize()).isEqualTo(1);
    }

    @Test
    void updateSession() {
        SessionUser sessionUser = new SessionUser(5L, 100L, 1L, LocalDateTime.now());
        assertThat(userService.updateSession(sessionUser)).isEqualTo(1);
    }
}