package nextstep.users.domain;

import java.util.ArrayList;
import java.util.List;

public class NextStepUsers {
  private final List<NextStepUser> nextStepUsers;

  public NextStepUsers() {
    this.nextStepUsers = new ArrayList<>();
  }

  public NextStepUsers(List<NextStepUser> nextStepUsers) {
    this.nextStepUsers = nextStepUsers;
  }

  public int currentEnrollment() {
    return nextStepUsers.size();
  }

  public void enroll(NextStepUser nextStepUser) {
    nextStepUsers.add(nextStepUser);
  }
}
