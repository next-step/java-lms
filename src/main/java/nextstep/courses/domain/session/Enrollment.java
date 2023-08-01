package nextstep.courses.domain.session;

import nextstep.courses.domain.registration.Registrations;

public class Enrollment {

  private SessionStatus sessionStatus;

  private boolean recruiting;

  private final SessionType sessionType;

  private final int maxRecruitment;

  public Enrollment(SessionStatus sessionStatus, boolean recruiting,
      SessionType sessionType, int maxRecruitment) {
    this.sessionStatus = sessionStatus;
    this.recruiting = recruiting;
    this.sessionType = sessionType;
    validateMaxRecruitment(maxRecruitment);
    this.maxRecruitment = maxRecruitment;
  }

  private void validateMaxRecruitment(int maxRecruitment) {
    if (maxRecruitment < 1) {
      throw new IllegalArgumentException();
    }
  }

  public boolean isRegistrationOpened() {
    return recruiting && !sessionStatus.equals(SessionStatus.COMPLETION);
  }

  public void changeSessionStatus(SessionStatus sessionStatus) {
    this.sessionStatus = sessionStatus;
  }

  public void recruitOpen() {
    recruiting = true;
  }

  public void recruitClose() {
    recruiting = false;
  }

  public boolean isRegistrationFulled(Registrations registrations) {
    return registrations.isRegistrationFulled(maxRecruitment);
  }

  public SessionStatus getSessionStatus() {
    return sessionStatus;
  }

  public boolean isRecruiting() {
    return recruiting;
  }

  public SessionType getSessionType() {
    return sessionType;
  }

  public int getMaxRecruitment() {
    return maxRecruitment;
  }
}
