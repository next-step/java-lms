package nextstep.courses.application;

import static org.assertj.core.api.Assertions.assertThat;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class SessionSignUpServiceTest {

  @Autowired
  private SessionSignUpService sessionSignUpService;

  @Test
  void signUp() {
    Long rowId = sessionSignUpService.signUp(200L, "sanjigi");
    assertThat(rowId).isNotNull();
  }
}