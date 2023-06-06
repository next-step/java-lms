package nextstep.sessions.domain;

public enum SessionStatus {
  READY, RECRUITING, END;

  public static void isRecruitingOrThrow(SessionStatus status) {
    if (RECRUITING != status) {
      throw new IllegalArgumentException("모집중인 세션만 참여할 수 있습니다.");
    }
  }
}
