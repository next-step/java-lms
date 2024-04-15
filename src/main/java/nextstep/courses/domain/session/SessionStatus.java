package nextstep.courses.domain.session;

public enum SessionStatus {
  READY,
  IN_PROGRESS,
  END;

  public boolean isInProgressStatus() {
    return this.equals(IN_PROGRESS);
  }
}
