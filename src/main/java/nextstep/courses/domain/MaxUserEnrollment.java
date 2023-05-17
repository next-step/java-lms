package nextstep.courses.domain;

import nextstep.users.domain.NextStepUsers;

public class MaxUserEnrollment {

  private static final String MAX_ENROLLMENT_MESSAGE = "해당 세션의 수강 인원이 만석되었습니다.";
  private final int maxUserEnrollment;

  public MaxUserEnrollment(int maxUserEnrollment) {
    this.maxUserEnrollment = maxUserEnrollment;
  }

  public void validateUserEnrollment(NextStepUsers nextStepUsers) {
    if (nextStepUsers.currentEnrollment() >= maxUserEnrollment) {
      throw new IllegalArgumentException(MAX_ENROLLMENT_MESSAGE);
    }
  }
}
