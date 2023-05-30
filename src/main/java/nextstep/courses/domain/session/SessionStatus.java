package nextstep.courses.domain.session;

public class SessionStatus {
  private final SessionProgressStatus progressStatus;
  private final SessionRecruitStatus recruitStatus;

  public SessionStatus(SessionProgressStatus progressStatus, SessionRecruitStatus recruitStatus) {
    this.progressStatus = progressStatus;
    this.recruitStatus = recruitStatus;
  }

  public boolean isEnrollable() {
    if(isLegacyProgress() && progressStatus == SessionProgressStatus.RECRUITING) {
      return true;
    }

    if (isLegacyProgress()) {
      return false;
    }

    return recruitStatus == SessionRecruitStatus.RECRUIT;
  }

  public boolean isNotEnrollable() {
    return !this.isEnrollable();
  }

  public String getProgressStatusName() {
    return progressStatus.getProgressStatusName();
  }

  public String getRecruitStatusName() {
    return recruitStatus.getRecruitStatusName();
  }

  private boolean isLegacyProgress() {
    return recruitStatus == null;
  }
}
