package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseTime;

public class Session extends BaseTime {
  private Long id;
  private SessionPeriod sessionPeriod;
  private SessionInfo sessionInfo;
  private CoverImage coverImage;

  private Session() {
  }

  public Session(Long id, SessionPeriod sessionPeriod, SessionInfo sessionInfo, CoverImage coverImage) {
    this.id = id;
    this.sessionPeriod = sessionPeriod;
    this.sessionInfo = sessionInfo;
    this.coverImage = coverImage;
  }

  public Long getId() {
    return id;
  }

  public String getSessionTitle() {
    return sessionInfo.getSessionTitle();
  }

  public SessionStatus getSessionStatus() {
    return sessionInfo.getSessionStatus();
  }

  public SessionPeriod getSessionPeriod() {
    return sessionPeriod;
  }

  public Long getSessionAmount() {
    return sessionInfo.getSessionAmount();
  }

  public Integer getStudentMaxCount() {
    return sessionInfo.getStudentMaxCount();
  }

  public Integer getTotalStudentCount() {
    return sessionInfo.getTotalStudentCount();
  }

  public boolean checkRegisterPossibleStatus() {
    return sessionInfo.checkRegisterPossibleStatus();
  }

  public boolean checkPayAmount(Long payAmount) {
    return sessionInfo.checkPayAmount(payAmount);
  }

  public void addStudentCount() {
    sessionInfo.addStudentCount();
  }

  public boolean isFree() {
    return sessionInfo.getIsFree();
  }
}
