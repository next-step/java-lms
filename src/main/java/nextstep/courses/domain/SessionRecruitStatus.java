package nextstep.courses.domain;

import java.util.List;
import java.util.stream.Stream;

public enum SessionRecruitStatus {

  RECRUITING("모집중"),
  NOT_RECRUITING("비모집중");

  private final String description;

  SessionRecruitStatus(String description) {
    this.description = description;
  }

  /**
   * 혹여나 session_recuit_status가 null이 들어올 경우를 대비해 null-safe하게 구현
   * table의 기본값에 맞게 NOT_RECRUITING으로 초기화
   */
  public static SessionRecruitStatus of(String sessionRecruitStatus) {
    return Stream.of(values())
        .filter(it -> it.name().equals(sessionRecruitStatus))
        .findFirst()
        .orElse(NOT_RECRUITING);
  }

  public boolean isRecruiting() {
    return this == RECRUITING;
  }

  public boolean isNotRecruiting() {
    return this == NOT_RECRUITING;
  }


  public void validateEnrollAvailable() {
    if (isNotRecruiting()) {
      throw new IllegalArgumentException("비모집중인 세션은 수강 신청할 수 없습니다.");
    }
  }
}
