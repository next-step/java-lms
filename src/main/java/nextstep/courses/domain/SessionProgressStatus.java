package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;

public enum SessionProgressStatus {
  PREPARING("준비중"),
  ACCEPTING("모집중"),
  IN_PROGRESSING("진행중"),
  ENDING("종료");

  private static final String ILLEGAL_STATUS_MESSAGE = "준비중, 모집중, 종료만 가능합니다.";
  private static final Map<String, SessionProgressStatus> BY_STATUS = new HashMap<>();

  static {
    for (SessionProgressStatus sessionProgressStatus : values()) {
      BY_STATUS.put(sessionProgressStatus.status, sessionProgressStatus);
    }
  }

  private final String status;

  SessionProgressStatus(String status) {
    this.status = status;
  }

  public String status() {
    return status;
  }

  public static SessionProgressStatus valueOfSessionStatus(String sessionStatus) {
    if (!hasContainStatus(sessionStatus)) {
      throw new IllegalArgumentException(ILLEGAL_STATUS_MESSAGE);
    }

    return BY_STATUS.get(sessionStatus);
  }

  public boolean canEnroll() {
    return this == ACCEPTING;
  }

  public boolean isEnrollStatus() {
    return this == ACCEPTING || this == IN_PROGRESSING;
  }

  private static boolean hasContainStatus(String status) {
    return BY_STATUS.containsKey(status);
  }
}
