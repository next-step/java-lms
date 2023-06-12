package nextstep.sessions.domain;

import java.time.LocalDateTime;

public class SessionDate {
  private LocalDateTime startDateTime;
  private LocalDateTime endDateTime;

  public SessionDate(LocalDateTime startDateTime, LocalDateTime endDateTime) {
    this.startDateTime = startDateTime;
    this.endDateTime = endDateTime;
  }

  public void validateInit() {
    if (startDateTime.isAfter(endDateTime)) {
      throw new IllegalArgumentException("수강신청 종료 일자는 수강신청 시작 일자보다 빠를 수 없습니다");
    }
  }

  public void validateEnrolment(LocalDateTime enrolmentDateTime) {
    if (enrolmentDateTime.isBefore(startDateTime) || enrolmentDateTime.isAfter(endDateTime)) {
      throw new IllegalStateException("수강신청 기간이 아닙니다");
    }
  }

  public LocalDateTime getStartDateTime() {
    return this.startDateTime;
  }

  public LocalDateTime getEndDateTime() {
    return this.endDateTime;
  }

  @Override
  public String toString() {
    return "SessionDate{" +
        "startDateTime=" + startDateTime +
        ", endDateTime=" + endDateTime +
        '}';
  }
}
