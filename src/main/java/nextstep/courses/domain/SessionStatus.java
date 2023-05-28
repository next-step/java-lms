package nextstep.courses.domain;

public class SessionStatus {

  private SessionProgressStatus progressStatus;
  private SessionRecruitmentStatus recruitmentStatus;

  public SessionStatus(SessionProgressStatus progressStatus, SessionRecruitmentStatus recruitmentStatus) {
    this.progressStatus = progressStatus;
    this.recruitmentStatus = recruitmentStatus;
  }

  public boolean canEnroll() {
    return progressStatus.isEnrollStatus() && recruitmentStatus.isRecruitingStatus();
  }

  public void ending() {
    this.progressStatus = SessionProgressStatus.ENDING;
  }

  public String getProgressStatus() {
    return progressStatus.status();
  }

  public String getRecruitmentStatus() {
    return recruitmentStatus.status();
  }
}
