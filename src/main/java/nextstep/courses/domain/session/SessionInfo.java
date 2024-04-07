package nextstep.courses.domain.session;

public class SessionInfo {

  private static final int MIN_STUDENT_COUNT = 1;
  public static final String INVALID_STUDENT_COUNT = "최소 수강 신청 인원을 넘지 못했습니다. input: %s";
  public static final String SESSION_TITLE_IS_INCORRECT = "강의 명이 올바르지 않습니다. 다시 확인해주세요. input: %s";

  private String sessionTitle;
  private SessionStatus sessionStatus;
  private boolean isFree;
  private Integer studentMaxCount;

  public SessionInfo(String sessionTitle, SessionStatus sessionStatus, boolean isFree, Integer studentMaxCount) {
    valid(sessionTitle, isFree, studentMaxCount);
    this.sessionTitle = sessionTitle;
    this.sessionStatus = sessionStatus;
    this.isFree = isFree;
    this.studentMaxCount = studentMaxCount;
  }

  // 무료 강의는 최대 수강 인원 제한이 없다.
  // 유료 강의는 강의 최대 수강 인원을 초과할 수 없다.
  public boolean valid(String sessionTitle, boolean isFree, Integer studentMaxCount) {
    if (sessionTitle == null || sessionTitle.isBlank()) {
      throw new IllegalArgumentException(String.format(SESSION_TITLE_IS_INCORRECT, sessionTitle));
    }

    if (!isFree && studentMaxCount < MIN_STUDENT_COUNT) {
      throw new IllegalArgumentException(String.format(INVALID_STUDENT_COUNT, studentMaxCount));
    }
    return true;
  }

  public String getSessionTitle() {
    return sessionTitle;
  }

  public SessionStatus getSessionStatus() {
    return sessionStatus;
  }

  public boolean getIsFree() {
    return isFree;
  }

  public Integer getStudentMaxCount() {
    return studentMaxCount;
  }

  public boolean checkApplyPossible() {
    return SessionStatus.IN_PROGRESS.equals(sessionStatus);
  }
}
