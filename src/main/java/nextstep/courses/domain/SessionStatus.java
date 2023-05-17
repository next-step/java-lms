package nextstep.courses.domain;

public enum SessionStatus {
  PREPARING("준비중"),
  ACCEPTING("모집중"),
  ENDING("종료");

  private static final String NOT_ACCEPTING_MESSAGE = "수강신청은 강의 상태가 모집중일 때만 가능합니다. 현재 수강 상태 : ";

  private final String status;

  SessionStatus(String status) {
    this.status = status;
  }

  public void validateAcceptingStatus() {
    if (this != ACCEPTING) {
      throw new IllegalArgumentException(NOT_ACCEPTING_MESSAGE + status);
    }
  }
}
