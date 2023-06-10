package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionPeriod {

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
    validateDate(startDate, endDate);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  private void validateDate(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate == null || endDate == null) {
      throw new IllegalArgumentException();
    }

    if (endDate.isBefore(startDate)) {
      throw new IllegalArgumentException();
    }
  }
}
