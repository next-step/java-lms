package nextstep.courses.domain;

public enum SessionStatus {

  PREPARING("준비중"),
  RECRUITING("모집중"),
  END("종료");

  private final String description;

  SessionStatus(String description) {
    this.description = description;
  }

  public void canEnroll() {
    if (this == PREPARING) {
      throw new IllegalArgumentException("준비중인 세션은 수강 신청할 수 없습니다.");
    }

    if (this == END) {
      throw new IllegalArgumentException("종료된 세션은 수강 신청할 수 없습니다.");
    }
  }

  public boolean isPreparing() {
    return this == PREPARING;
  }
}
