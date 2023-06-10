package nextstep.courses.domain.registration;

public class RegistrationStatus {

  private boolean canceled = false;

  public RegistrationStatus(boolean canceled) {
    this.canceled = canceled;
  }

  public void cancel() {
    canceled = true;
  }

  public boolean isCanceled() {
    return canceled;
  }
}
