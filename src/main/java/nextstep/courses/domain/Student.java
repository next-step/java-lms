package nextstep.courses.domain;

import nextstep.users.domain.NsUser;

public class Student {

  private final NsUser user;

  private final ApproveStatus approveStatus;

  public Student(NsUser user, ApproveStatus approveStatus) {
    this.user = user;
    this.approveStatus = approveStatus;
  }

  public Student(NsUser user) {
    this(user, ApproveStatus.WAITING);
  }

  public boolean isApproved() {
    return approveStatus.isApproved();
  }

  public boolean isWaiting() {
    return approveStatus.isWaiting();
  }
}
