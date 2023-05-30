package nextstep.courses.domain;

public enum ApproveStatus {

  APPROVED("승인"),
  WAITING("대기"),
  REJECTED("거절");

  private final String description;

  ApproveStatus(String description) {
    this.description = description;
  }

  public boolean isApproved() {
    return this == APPROVED;
  }

  public boolean isWaiting() {
    return this == WAITING;
  }

  public boolean isRejected() {
    return this == REJECTED;
  }



}
