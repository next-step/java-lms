package nextstep.courses.service;

import nextstep.courses.domain.Session;
import nextstep.users.domain.User;
import nextstep.users.domain.UserRepository;
import nextstep.users.exception.UserNotFoundException;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.transaction.annotation.Transactional;

@SpringBootTest
@Transactional
class SessionServiceTest {
    @Autowired
    private SessionService sessionService;

    @Autowired
    private UserRepository userRepository;

    @Test
    @DisplayName("id를 통해 조회할 수 있다.")
    public void findById_test() {
        long sessionId = 1;
        Session session = sessionService.findSessionById(sessionId);

        Assertions.assertThat(session.getId()).isEqualTo(sessionId);
    }

    @Test
    @DisplayName("유저를 세션에 등록할 수 있다.")
    public void enrollUserToSession_test() {
        Session beforeSession = sessionService.findSessionById(1L);
        User user = userRepository.findById(1L)
                .orElseThrow(() -> new UserNotFoundException("유저를 찾을 수 없습니다."));

        sessionService.enrollUserToSession(beforeSession.getId(), user.getId());

        Session afterSession = sessionService.findSessionById(beforeSession.getId());
        Assertions.assertThat(afterSession.getUsers()).contains(user);
    }

}