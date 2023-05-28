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
}
