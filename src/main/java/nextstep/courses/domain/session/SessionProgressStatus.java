package nextstep.courses.domain.session;

public enum SessionProgressStatus {
  PREPARING("준비중"),

  @Deprecated
  RECRUITING("모집중"),
  ONGOING("진행중"),
  END("종료")
  ;

  private final String statusName;

  SessionProgressStatus(String statusName) {
    this.statusName = statusName;
  }

  public String getProgressStatusName() {
    return statusName;
  }
}
