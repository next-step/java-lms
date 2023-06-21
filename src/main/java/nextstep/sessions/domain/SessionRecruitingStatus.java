package nextstep.sessions.domain;

import java.util.Arrays;

public enum SessionRecruitingStatus {
  NOTHING(1L), RECRUITING(2L);

  // db의 pk와 같다
  // order를 추가하면 DB에 정보를 추가했을 때, 코드에도 추가해야 하는 불편함이 발생하는 것 아닐까?
  // 어차피 로직을 넣어야 하기 때문에 애플리케이션에 코드를 추가하는 것이 필요함. 따라서 order 정보를 주어 pk를 굳이 조회할 필요가 없도록 설계
  private Long order;

  SessionRecruitingStatus(Long order) {
    this.order = order;
  }

  public static void isRecruitingOrThrow(SessionRecruitingStatus status) {
    if (RECRUITING != status) {
      throw new IllegalStateException("모집중인 강의만 신청 가능합니다");
    }
  }

  public static SessionRecruitingStatus from(Long order) {
    return Arrays.stream(SessionRecruitingStatus.values()).filter(v -> v.order.equals(order))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  public Long getOrder() {
    return this.order;
  }
}
