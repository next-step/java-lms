package nextstep.courses.domain;

public class Session {
  private final SessionInformation info;
  private final EnrollmentManager enrollment;

  public Session(SessionInformation info, EnrollmentManager enrollment) {
    this.info = info;
    this.enrollment = enrollment;
  }

  public void register(nextstep.payments.domain.Payment payment) {
    enrollment.register(payment);
  }

  public void updateStatus(SessionStatus status) {
    enrollment.updateStatus(status);
  }

  public int currentEnrollment() {
    return enrollment.count();
  }

  public SessionInformation info() {
    return info;
  }

  public EnrollmentManager enrollment() {
    return enrollment;
  }
}
