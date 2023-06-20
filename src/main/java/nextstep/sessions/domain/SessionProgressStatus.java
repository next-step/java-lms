package nextstep.sessions.domain;

import java.util.Arrays;

public enum SessionProgressStatus {
  READY(1L), PROGRESS(2L), END(3L);

  private Long order;

  SessionProgressStatus(Long order) {
    this.order = order;
  }

  public static SessionProgressStatus from(Long order) {
    return Arrays.stream(SessionProgressStatus.values()).filter(v -> v.order.equals(order))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  public Long getOrder() {
    return this.order;
  }
}
