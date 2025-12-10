package nextstep.courses.domain.session;

public enum SessionProgressState {
  PREPARING,
  IN_PROGRESS,
  FINISHED;
  public boolean canCreateEnrollment() {
    return this == PREPARING || this == IN_PROGRESS;
  }

  public SessionProgressState start() {
    if (this != PREPARING) {
      throw new IllegalStateException("준비중인 강의만 시작할 수 있습니다.");
    }
    return IN_PROGRESS;
  }

  public SessionProgressState finish() {
    if (this != IN_PROGRESS) {
      throw new IllegalStateException("진행중인 강의만 종료할 수 있습니다.");
    }
    return FINISHED;
  }
}
