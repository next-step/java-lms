package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class SessionApply {

  private Status status;

  private int maxEnrollment;

  private List<NsUser> enrollmentUsers;

  public SessionApply(Status status, int maxEnrollment) {
    this.status = status;
    this.maxEnrollment = maxEnrollment;
    enrollmentUsers = new ArrayList<>();
  }

  public boolean isRecruiting() {
    return status == Status.RECRUITING;
  }

  public void enrollment(NsUser user) {
    enrollmentUsers.add(user);
  }

  public boolean isMaxEnrollment() {
    return enrollmentUsers.size() >= maxEnrollment;
  }

  public boolean isApply(NsUser user) {
    return enrollmentUsers.contains(user);
  }
}
