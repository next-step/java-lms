package nextstep.courses.domain;

import java.util.Objects;
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

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    Student student = (Student) o;
    return Objects.equals(user, student.user) && approveStatus == student.approveStatus;
  }

  @Override
  public int hashCode() {
    return Objects.hash(user, approveStatus);
  }

  public Student approved(ApproveStatus approveStatus) {
    if (this.approveStatus.isApproved()) {
      throw new IllegalArgumentException("이미 승인된 상태입니다.");
    }
    return new Student(user, approveStatus);
  }

  public Student rejected(ApproveStatus rejected) {
    if (this.approveStatus.isRejected()) {
      throw new IllegalArgumentException("이미 거절된 상태입니다.");
    }
    return new Student(user, rejected);
  }

  public boolean isRejected() {
    return approveStatus.isRejected();
  }
}
