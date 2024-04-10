package nextstep.courses.domain.session;

import nextstep.courses.domain.BaseTime;

import java.util.Objects;

public class Session extends BaseTime {
  private Long id;
  private SessionPeriod sessionPeriod;
  private SessionInfo sessionInfo;
  private CoverImage coverImage;

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

  public int getSessionAmount() {
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

  public boolean checkPayAmount(int payAmount) {
    return sessionInfo.checkPayAmount(payAmount);
  }

  public void addStudentCount() {
    sessionInfo.addStudentCount();
  }

  public boolean isFree() {
    return sessionInfo.getIsFree();
  }

  @Override
  public boolean equals(Object o) {
    if (this == o) return true;
    if (o == null || getClass() != o.getClass()) return false;
    Session session = (Session) o;
    return Objects.equals(sessionPeriod, session.sessionPeriod) && Objects.equals(sessionInfo, session.sessionInfo);
  }

  @Override
  public int hashCode() {
    return Objects.hash(sessionPeriod, sessionInfo);
  }
}
