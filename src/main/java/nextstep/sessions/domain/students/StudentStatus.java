package nextstep.sessions.domain.students;

import java.util.Arrays;

public enum StudentStatus {
  WAITING(1L), ACCEPTED(2L), REJECTED(3L);

  private Long order;

  StudentStatus(Long order) {
    this.order = order;
  }

  public static StudentStatus from(Long order) {
    return Arrays.stream(StudentStatus.values()).filter(v -> v.order.equals(order))
        .findFirst()
        .orElseThrow(IllegalArgumentException::new);
  }

  public Long getOrder() {
    return this.order;
  }

  public boolean isRejected() {
    return this == REJECTED;
  }
}
