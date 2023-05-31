package nextstep.session.domain;

import java.util.ArrayList;
import java.util.List;
import nextstep.users.domain.NsUser;

public class SessionApply {

  private Status status;

  private int maxEnrollment;

  private int enrollment;

  private List<NsUser> enrollmentUsers;

  public SessionApply(Status status, int maxEnrollment, int enrollment) {
    this.status = status;
    this.maxEnrollment = maxEnrollment;
    this.enrollment = enrollment;
    enrollmentUsers = new ArrayList<>();
  }

  public boolean isRecruiting() {
    return status == Status.RECRUITING;
  }

  public void enrollment(NsUser user) {
    enrollment++;
    enrollmentUsers.add(user);
  }

  public boolean isMaxEnrollment() {
    return enrollment >= maxEnrollment;
  }

  public boolean isApply(NsUser user) {
    return enrollmentUsers.contains(user);
  }
}
