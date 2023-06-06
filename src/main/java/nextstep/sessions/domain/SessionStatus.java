package nextstep.sessions.domain;

public enum SessionStatus {
  READY, RECRUITING, END;

  public static void isRecruitingOrThrow(SessionStatus status) {
    if (RECRUITING != status) {
      throw new IllegalStateException("모집중인 강의만 신청 가능합니다");
    }
  }
}
