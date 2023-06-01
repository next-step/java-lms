package nextstep.courses.domain;

import java.util.Arrays;
import java.util.stream.Stream;

public enum ApproveStatus {

  APPROVED("승인"),
  WAITING("대기"),
  REJECTED("거절");

  private final String description;

  ApproveStatus(String description) {
    this.description = description;
  }

  /**
   * approveStatus 가 없는 경우, database table default 값에 맞게 WAITING 상태로 관리하고자 함
   */
  public static ApproveStatus of(String approveStatus) {
    return Stream.of(values())
        .filter(v -> v.name().equals(approveStatus))
        .findFirst()
        .orElse(WAITING);
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
