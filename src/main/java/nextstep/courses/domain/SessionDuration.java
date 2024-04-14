package nextstep.courses.domain;


import java.time.LocalDate;

public class SessionDuration {

  private LocalDate startDate;
  private LocalDate endDate;

  public SessionDuration(LocalDate startDate, LocalDate endDate) {
    validateSessionDate(startDate, endDate);
    this.startDate = startDate;
    this.endDate = endDate;
  }

  private void validateSessionDate(LocalDate startDate, LocalDate endDate) {
    if (startDate.isAfter(endDate)) {
      throw new IllegalArgumentException("시작일보다 종료일이 먼저올 수 없습니다.");
    }
  }

  public LocalDate getStartDate() {
    return startDate;
  }

  public LocalDate getEndDate() {
    return endDate;
  }

  public boolean isRecruitingNow() {
    LocalDate now = LocalDate.now();
    if (now.isEqual(startDate) || now.isAfter(startDate) || now.isBefore(endDate)) {
      return true;
    }
    return false;
  }
}
