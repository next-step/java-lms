package nextstep.courses.domain.session;

public enum SessionStatus {
  PREPARING("준비중"),
  RECRUITING("모집중"),
  END("종료")
  ;

  private final String statusName;

  SessionStatus(String statusName) {
    this.statusName = statusName;
  }

  public String getStatusName() {
    return statusName;
  }
}
