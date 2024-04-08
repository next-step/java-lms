package nextstep.courses.domain.session;

public class SessionInfo {
  public static final String SESSION_TITLE_IS_INCORRECT = "강의 명이 올바르지 않습니다. 다시 확인해주세요. input: %s";

  private String sessionTitle;
  private SessionStatus sessionStatus;
  private SessionEnrollmentInfo sessionEnrollmentInfo;

  public SessionInfo(String sessionTitle, SessionStatus sessionStatus, boolean isFree, Long sessionAmount, Integer studentMaxCount) {
    valid(sessionTitle);
    this.sessionTitle = sessionTitle;
    this.sessionStatus = sessionStatus;
    this.sessionEnrollmentInfo = new SessionEnrollmentInfo(isFree, sessionAmount, studentMaxCount);
  }

  private void valid(String sessionTitle) {
    if (sessionTitle == null || sessionTitle.isBlank()) {
      throw new IllegalArgumentException(String.format(SESSION_TITLE_IS_INCORRECT, sessionTitle));
    }
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

  public Long getSessionAmount() {
    return sessionEnrollmentInfo.getSessionAmount();
  }

  public boolean checkRegisterPossibleStatus() {
    return sessionStatus.isInProgressStatus();
  }

  public boolean checkPayAmount(Long payAmount) {
    return sessionEnrollmentInfo.getSessionAmount().equals(payAmount);
  }

  public void addStudentCount() {
    sessionEnrollmentInfo.addStudentCount();
  }
}
