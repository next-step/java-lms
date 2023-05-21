package nextstep.users.domain;

import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

public class NextStepUsersTest {

  private NextStepUsers nextStepUsers;

  @BeforeEach
  public void setUp() {
    nextStepUsers = new NextStepUsers(1);
    nextStepUsers.enroll(NextStepUserTest.JAVAJIGI);

  }

  @Test
  public void 수강인원_체크() {
    Assertions.assertThatThrownBy(() -> nextStepUsers.enroll(NextStepUserTest.SANJIGI))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 세션의 수강 인원이 만석되었습니다.");
  }
}
