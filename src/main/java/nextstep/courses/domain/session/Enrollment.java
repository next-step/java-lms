package nextstep.courses.domain.session;

import nextstep.courses.domain.registration.Registrations;

public class Enrollment {

  private SessionStatus sessionStatus = SessionStatus.PREPARATION;

  private SessionType sessionType;

  private int maxRecruitment;

  public Enrollment(SessionStatus sessionStatus, SessionType sessionType, int maxRecruitment) {
    this.sessionStatus = sessionStatus;
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
    return !sessionStatus.equals(SessionStatus.RECRUITMENT);
  }

  public void registerOpen() {
    sessionStatus = SessionStatus.RECRUITMENT;
  }

  public void registerClose() {
    sessionStatus = SessionStatus.COMPLETION;
  }

  public boolean isRegistrationFulled(Registrations registrations) {
    return registrations.isRegistrationFulled(maxRecruitment);
  }
}
