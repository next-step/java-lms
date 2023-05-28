package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionPeriod {

  private final LocalDateTime startAt;
  private final LocalDateTime finishAt;

  public SessionPeriod(LocalDateTime startAt, LocalDateTime finishAt) {
    this.startAt = startAt;
    this.finishAt = finishAt;
  }

  public LocalDateTime getStartAt() {
    return startAt;
  }

  public LocalDateTime getFinishAt() {
    return finishAt;
  }
}
