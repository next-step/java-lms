package nextstep.courses.domain;

import java.util.HashMap;
import java.util.Map;

public enum SessionRecruitmentStatus {
  RECRUITING("모집중"),
  NOT_RECRUITING("비모집중");

  private static final String RECRUITING_STATUS = "모집중";
  private static final String ILLEGAL_STATUS_MESSAGE = "모집중, 비모집중만 가능합니다.";
  private static final Map<String, SessionRecruitmentStatus> BY_RECRUITMENT_STATUS = new HashMap<>();

  static {
    for (SessionRecruitmentStatus sessionRecruitmentStatus : values()) {
      BY_RECRUITMENT_STATUS.put(sessionRecruitmentStatus.recruitmentStatus, sessionRecruitmentStatus);
    }
  }

  private final String recruitmentStatus;

  SessionRecruitmentStatus(String recruitmentStatus) {
    this.recruitmentStatus = recruitmentStatus;
  }

  public static SessionRecruitmentStatus valueOfSessionRecruitmentStatus(String recruitmentStatus) {
    if (recruitmentStatus == null) {
      return BY_RECRUITMENT_STATUS.get(RECRUITING_STATUS);
    }

    if (!hasRecruitmentStatus(recruitmentStatus)) {
      throw new IllegalArgumentException(ILLEGAL_STATUS_MESSAGE);
    }

    return BY_RECRUITMENT_STATUS.get(recruitmentStatus);
  }

  private static boolean hasRecruitmentStatus(String recruitmentStatus) {
    return BY_RECRUITMENT_STATUS.containsKey(recruitmentStatus);
  }

  public String status() {
    return recruitmentStatus;
  }

  public boolean isRecruitingStatus() {
    return this == RECRUITING;
  }
}
