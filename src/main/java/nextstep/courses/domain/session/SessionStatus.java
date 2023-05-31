package nextstep.courses.domain.session;

import java.util.List;

public class SessionStatus {
  private final SessionProgressStatus progressStatus;
  private final SessionRecruitStatus recruitStatus;

  private static final List<SessionProgressStatus> ENROLL_ALLOWED_PROGRESS_STATUS
      = List.of(SessionProgressStatus.RECRUITING, SessionProgressStatus.ONGOING);

  public SessionStatus(SessionProgressStatus progressStatus, SessionRecruitStatus recruitStatus) {
    this.progressStatus = progressStatus;
    this.recruitStatus = recruitStatus;
  }

  public boolean isEnrollable() {
    if (isNewlyEnrollable()) {
      return true;
    }

    return isLegacyEnrollable();
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

  @Deprecated
  private boolean isLegacyEnrollable() {
    if (recruitStatus != null) {
      return false;
    }

    return ENROLL_ALLOWED_PROGRESS_STATUS.contains(progressStatus);
  }

  private boolean isNewlyEnrollable() {
    if(recruitStatus == null) {
      return false;
    }

    return ENROLL_ALLOWED_PROGRESS_STATUS.contains(progressStatus)
        && recruitStatus == SessionRecruitStatus.RECRUIT;
  }

  @Deprecated
  public boolean isLegacy() {
    return this.recruitStatus == null;
  }
}
