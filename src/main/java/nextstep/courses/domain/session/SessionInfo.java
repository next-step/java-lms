package nextstep.courses.domain.session;

public class SessionInfo {
  public static final String SESSION_TITLE_IS_INCORRECT = "강의 명이 올바르지 않습니다. 다시 확인해주세요. input: %s";

  private String sessionTitle;
  private SessionStatus sessionStatus;
  private SessionEnrollmentInfo sessionEnrollmentInfo;

  private SessionInfo(String sessionTitle, SessionStatus sessionStatus, SessionType sessionType, int sessionAmount) {
    valid(sessionTitle);
    this.sessionTitle = sessionTitle;
    this.sessionStatus = sessionStatus;
    this.sessionEnrollmentInfo = new SessionEnrollmentInfo(sessionType, sessionAmount);
  }

  private SessionInfo(String sessionTitle, SessionStatus sessionStatus, SessionType sessionType, int sessionAmount, int studentMaxCount) {
    valid(sessionTitle);
    this.sessionTitle = sessionTitle;
    this.sessionStatus = sessionStatus;
    this.sessionEnrollmentInfo = new SessionEnrollmentInfo(sessionType, sessionAmount, studentMaxCount);
  }

  private void valid(String sessionTitle) {
    if (sessionTitle == null || sessionTitle.isBlank()) {
      throw new IllegalArgumentException(String.format(SESSION_TITLE_IS_INCORRECT, sessionTitle));
    }
  }

  public static SessionInfo newFreeSession(String sessionTitle, SessionStatus sessionStatus) {
    return new SessionInfo(sessionTitle, sessionStatus, SessionType.FREE, 0);
  }

  public static SessionInfo newPaidSession(String sessionTitle, SessionStatus sessionStatus, int sessionAmount, int studentMaxCount) {
    return new SessionInfo(sessionTitle, sessionStatus, SessionType.PAID, sessionAmount, studentMaxCount);
  }

  public String getSessionTitle() {
    return sessionTitle;
  }

  public SessionStatus getSessionStatus() {
    return sessionStatus;
  }

  public boolean getIsFree() {
    return sessionEnrollmentInfo.isFree();
  }

  public Integer getStudentMaxCount() {
    return sessionEnrollmentInfo.getStudentMaxCount();
  }

  public Integer getTotalStudentCount() {
    return sessionEnrollmentInfo.getTotalStudentCount();
  }

  public int getSessionAmount() {
    return sessionEnrollmentInfo.getSessionAmount();
  }

  public boolean checkRegisterPossibleStatus() {
    return sessionStatus.isInProgressStatus();
  }

  public boolean checkPayAmount(int payAmount) {
    return sessionEnrollmentInfo.getSessionAmount() == payAmount;
  }

  public void addStudentCount() {
    sessionEnrollmentInfo.addStudentCount();
  }
}
