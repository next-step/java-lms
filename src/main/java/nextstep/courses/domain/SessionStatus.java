package nextstep.courses.domain;

public enum SessionStatus {
  PREPARING("준비중"),
  ACCEPTING("모집중"),
  ENDING("종료");

  private final String status;

  SessionStatus(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }

  public boolean canEnroll() {
    return this == ACCEPTING;
  }
}
