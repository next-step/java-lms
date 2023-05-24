package nextstep.courses.domain;

import org.springframework.cglib.core.Local;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionPeriod {

  private static final String ILLEGAL_SESSION_DATE_MESSAGE = "종료일은 시작일 이후여야 합니다. 현재 시작일 : ";
  private static final String CLOSED_DATE_MESSAGE = ", 현재 종료일 : ";
  private final LocalDateTime startDate;
  private final LocalDateTime endDate;

  public SessionPeriod(LocalDateTime startDate, LocalDateTime endDate) {
    validatePeriod(startDate, endDate);

    this.startDate = startDate;
    this.endDate = endDate;
  }

  private void validatePeriod(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException(ILLEGAL_SESSION_DATE_MESSAGE + currentDate(startDate) + CLOSED_DATE_MESSAGE + currentDate(endDate));
    }
  }

  private String currentDate(LocalDateTime localDateTime) {
    return localDateTime.format(DateTimeFormatter.ISO_DATE);
  }

  public LocalDateTime getStartDate() {
    return startDate;
  }

  public LocalDateTime getEndDate() {
    return endDate;
  }
}
