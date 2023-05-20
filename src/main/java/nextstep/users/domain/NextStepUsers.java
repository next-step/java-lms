package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class NextStepUsers {

  private static final String MAX_ENROLLMENT_MESSAGE = "해당 세션의 수강 인원이 만석되었습니다.";
  private final int maxUserEnrollment;
  private final List<NextStepUser> nextStepUsers;

  public NextStepUsers(int maxUserEnrollment) {
    this.nextStepUsers = new ArrayList<>();
    this.maxUserEnrollment = maxUserEnrollment;
  }

  public void enroll(NextStepUser nextStepUser) {
    validateUserEnrollment();

    nextStepUsers.add(nextStepUser);
  }

  public int currentEnrollment() {
    return nextStepUsers.size();
  }

  private void validateUserEnrollment() {
    if (nextStepUsers.size() >= maxUserEnrollment) {
      throw new IllegalArgumentException(MAX_ENROLLMENT_MESSAGE);
    }
  }
}
