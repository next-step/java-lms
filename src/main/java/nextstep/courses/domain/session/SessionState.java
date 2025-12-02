package nextstep.courses.domain.session;

public enum SessionState {
  PREPARING,
  RECRUITING,
  CLOSED;

  public SessionState open() {
    if (this != PREPARING) {
      throw new IllegalStateException("준비중인 강의만 모집을 시작할 수 있습니다.");
    }
    return RECRUITING;
  }

  public SessionState close() {
    if (this != RECRUITING) {
      throw new IllegalStateException("모집중인 강의만 종료할 수 있습니다.");
    }
    return CLOSED;
  }

  public boolean canEnroll() {
    return this == RECRUITING;
  }
}