package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionPeriod {

  public static final String INVALID_SESSION_PERIOD = "강의 시작일과 종료일을 다시 확인해주세요. startedAt: %s, endedAt: %s";
  public static final String INVALID_SESSION_STARTED_AT = "강의 시작일이 종료일과 같거나 이후일 수 없습니다. startedAt: %s, endedAt: %s";

  private LocalDateTime startedAt;
  private LocalDateTime endedAt;

  public SessionPeriod(LocalDateTime startedAt, LocalDateTime endedAt) {
    valid(startedAt, endedAt);
    this.startedAt = startedAt;
    this.endedAt = endedAt;
  }

  private void valid(LocalDateTime startedAt, LocalDateTime endedAt) {
    if (startedAt == null || endedAt == null) {
      throw new IllegalArgumentException(String.format(INVALID_SESSION_PERIOD, startedAt, endedAt));
    }

    if (startedAt.isAfter(endedAt) || startedAt.isEqual(endedAt)) {
      throw new IllegalArgumentException(String.format(INVALID_SESSION_STARTED_AT, startedAt, endedAt));
    }
  }

  public boolean isCorrectPeriod() {
    return (startedAt.isBefore(endedAt));
  }
}
