package nextstep.courses.domain.registration;

import java.util.Objects;

public class RegistrationInfo {

  private final Long id;

  private RegistrationStatus registrationStatus;

  public RegistrationInfo(Long id,
      RegistrationStatus registrationStatus) {
    this.id = id;
    this.registrationStatus = registrationStatus;
  }

  public void approval() {
    registrationStatus = RegistrationStatus.APPROVAL;
  }

  public boolean isApproval() {
    return registrationStatus.equals(RegistrationStatus.APPROVAL);
  }

  public void cancel() {
    registrationStatus = RegistrationStatus.CANCEL;
  }

  public boolean isCancel() {
    return registrationStatus.equals(RegistrationStatus.CANCEL);
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) {
      return true;
    }
    if (o == null || getClass() != o.getClass()) {
      return false;
    }
    RegistrationInfo that = (RegistrationInfo) o;
    return Objects.equals(id, that.id);
  }

  @Override
  public int hashCode() {
    return Objects.hash(id);
  }

  public Long getId() {
    return id;
  }

  public RegistrationStatus getRegistrationStatus() {
    return registrationStatus;
  }
}
