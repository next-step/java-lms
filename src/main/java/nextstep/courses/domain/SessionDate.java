package nextstep.courses.domain;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

public class SessionDate {

  private static final String ILLEGAL_SESSION_DATE_MESSAGE = "종료일은 시작일 이후여야 합니다. 현재 시작일 : ";
  private static final String CLOSED_DATE_MESSAGE = ", 현재 종료일 : ";
  private final LocalDateTime sessionDate;

  public SessionDate(LocalDateTime sessionDate) {
    this.sessionDate = sessionDate;
  }

  public void validateClosedDate(LocalDateTime closedDate) {
    if (sessionDate.isAfter(closedDate)) {
      throw new IllegalArgumentException(ILLEGAL_SESSION_DATE_MESSAGE + currentDate(sessionDate) + CLOSED_DATE_MESSAGE + currentDate(closedDate));
    }
  }

  private String currentDate(LocalDateTime localDateTime) {
    return localDateTime.format(DateTimeFormatter.ISO_DATE);
  }
}
