package nextstep.courses.domain.lectures;

import java.time.LocalDateTime;

public class RegistrationPeriod {
  private final LocalDateTime startedAt;
  private final LocalDateTime endedAt;

  public RegistrationPeriod(LocalDateTime startedAt, LocalDateTime endedAt) {
    if (endedAt.isBefore(startedAt)) {
      throw new IllegalArgumentException("강의 종료일은 강의 시작일보다 과거일 수 없습니다.");
    }
    this.startedAt = startedAt;
    this.endedAt = endedAt;
  }

  public LocalDateTime getStartedAt() {
    return startedAt;
  }

  public LocalDateTime getEndedAt() {
    return endedAt;
  }
}
