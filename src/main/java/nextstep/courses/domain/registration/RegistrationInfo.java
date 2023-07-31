package nextstep.courses.domain.registration;

public class RegistrationInfo {

  private final Long id;

  private boolean canceled = false;

  public RegistrationInfo(Long id, boolean canceled) {
    this.id = id;
    this.canceled = canceled;
  }

  public void cancel() {
    canceled = true;
  }

  public Long getId() {
    return id;
  }

  public boolean isCanceled() {
    return canceled;
  }
}
