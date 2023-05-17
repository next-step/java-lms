package nextstep.courses.domain;

import nextstep.users.domain.NextStepUserTest;
import nextstep.users.domain.NextStepUsers;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;

import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThatThrownBy;

public class MaxUserEnrollmentTest {

  private NextStepUsers nextStepUsers;
  private MaxUserEnrollment maxUserEnrollment;

  @BeforeEach
  public void setUp() {
    maxUserEnrollment = new MaxUserEnrollment(2);
    nextStepUsers = new NextStepUsers(
            Arrays.asList(NextStepUserTest.JAVAJIGI, NextStepUserTest.SANJIGI)
    );
  }

  @Test
  @DisplayName("최대 수강 인원 넘을 시 IllegalArgumentException throw")
  public void 수강_인원_초과() {
    assertThatThrownBy(() -> maxUserEnrollment.validateUserEnrollment(nextStepUsers))
            .isInstanceOf(IllegalArgumentException.class)
            .hasMessage("해당 세션의 수강 인원이 만석되었습니다.");
  }
}
