package nextstep.courses.infrastructure;

import nextstep.courses.domain.*;
import nextstep.users.domain.NsUserTest;
import nextstep.users.infrastructure.JdbcUserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.JdbcTest;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;
import java.util.stream.Collectors;

import static nextstep.courses.SessionFixture.강의_과정_1;
import static org.assertj.core.api.Assertions.assertThat;

@JdbcTest
class JdbcSessionRepositoryTest {
    private static final Logger LOGGER = LoggerFactory.getLogger(SessionRepository.class);
    @Autowired
    private JdbcTemplate jdbcTemplate;

    private SessionRepository sessionRepository;

    @BeforeEach
    void setUp() {
        sessionRepository = new JdbcSessionRepository(jdbcTemplate);
    }

    @Test
    void crud() {
        SessionUserService sessionUserService = new SessionUserServiceImpl(sessionRepository, new JdbcUserRepository(jdbcTemplate));
        Session session = 강의_과정_1();
        session.enrollSession(NsUserTest.JAVAJIGI);

        long count = sessionRepository.save(session);
        LOGGER.debug("Session count: {}", count);
        assertThat(count).isEqualTo(1);

        sessionRepository.saveSessionUser(session);
        List<SessionUser> sessionUsers = sessionRepository.findAllBySessionId(session.getId());
        LOGGER.debug("sessionUsers: {}", sessionUsers);
        assertThat(sessionUsers.size()).isEqualTo(1);

        List<String> users = sessionUsers.stream().map(sessionUser -> sessionUser.getNsUser().getUserId()).collect(Collectors.toList());

        sessionUserService.approve(session.getId(), users);
        Session savedSession = sessionRepository.findById(1L);
        assertThat(session.getSessionPayment().name()).isEqualTo(savedSession.getSessionPayment().name());
        LOGGER.debug("Session: {}", savedSession);

        List<SessionUser> sessionUsersForApprove = sessionRepository.findAllBySessionId(session.getId());
        LOGGER.debug("[approve] sessionUsers case: {}", sessionUsersForApprove);

        sessionUserService.reject(session.getId(), users);
        List<SessionUser> sessionUsersForReject = sessionRepository.findAllBySessionId(session.getId());
        LOGGER.debug("[reject] sessionUsers case: {}", sessionUsersForReject);

    }

}
