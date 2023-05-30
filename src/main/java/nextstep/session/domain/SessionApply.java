package nextstep.session.domain;

public class SessionApply {

  private Status status;

  private int maxEnrollment;

  private int enrollment;

  public SessionApply(Status status, int maxEnrollment, int enrollment) {
    this.status = status;
    this.maxEnrollment = maxEnrollment;
    this.enrollment = enrollment;
  }

  public boolean isRecruiting() {
    return status == Status.RECRUITING;
  }

  public void enrollment() {
    enrollment++;
  }

  public boolean isMaxEnrollment() {
    return enrollment >= maxEnrollment;
  }
}
