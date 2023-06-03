package nextstep.sessions.domain;

public enum SessionStatus {
  READY, RECRUITING, END;

  public static boolean isRecruiting(SessionStatus status) {
    return RECRUITING.equals(status);
  }
}
