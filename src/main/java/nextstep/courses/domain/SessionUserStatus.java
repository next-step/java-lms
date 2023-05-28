package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;

public enum SessionUserStatus {
  REQUEST("신청"),
  APPROVAL("승인"),
  REJECTION("거절");

  private static final String APPROVAL_STATUS = "승인";
  private static final String ILLEGAL_STATUS_MESSAGE = "신청, 승인, 거절만 가능합니다. 현재 상태 : ";
  private static final Map<String, SessionUserStatus> BY_STATUS = new HashMap<>();

  static {
    for (SessionUserStatus sessionUserStatus : values()) {
      BY_STATUS.put(sessionUserStatus.status, sessionUserStatus);
    }
  }

  private String status;

  SessionUserStatus(String status) {
    this.status = status;
  }

  public static SessionUserStatus valueOfSessionUserStatus(String status) {
    if (status == null) {
      return BY_STATUS.get(APPROVAL_STATUS);
    }

    if (!containsStatus(status)) {
      throw new IllegalArgumentException(ILLEGAL_STATUS_MESSAGE + status);
    }

    return BY_STATUS.get(status);
  }

  private static boolean containsStatus(String status) {
    return BY_STATUS.containsKey(status);
  }

  public String status() {
    return status;
  }
}
