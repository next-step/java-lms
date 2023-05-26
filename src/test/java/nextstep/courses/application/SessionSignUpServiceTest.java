package nextstep.courses.application;

import static org.assertj.core.api.Assertions.*;

import nextstep.courses.domain.SessionRepository;
import nextstep.users.domain.UserRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SessionSignUpServiceTest {

  @Autowired
  private SessionRepository sessionRepository;

  @Autowired
  private UserRepository userRepository;

  private SessionSignUpService sessionSignUpService;

  @BeforeEach
  void setUp() {
    sessionSignUpService = new SessionSignUpService(sessionRepository, userRepository);
  }


  @Test
  void signUp() {
    Long rowId = sessionSignUpService.signUp(200L, "sanjigi");
    assertThat(rowId).isNotNull();
  }
}