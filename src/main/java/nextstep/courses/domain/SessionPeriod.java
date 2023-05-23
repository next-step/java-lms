package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

  private final LocalDateTime startDateTime;
  private final LocalDateTime endDateTime;

  public SessionPeriod(LocalDateTime startDateTime, LocalDateTime endDateTime) {
    validateTime(startDateTime, endDateTime);
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }

  private void validateTime(LocalDateTime startDateTime, LocalDateTime endDateTime) {
    if (startDateTime.isAfter(endDateTime)) {
      throw new IllegalArgumentException("시작 시간이 종료 시간보다 늦을 수 없습니다.");
    }
  }

}
