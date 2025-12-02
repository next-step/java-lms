package nextstep.courses.domain.session;

import java.time.LocalDate;

public class SessionPeriod {
  private final LocalDate startDay;
  private final LocalDate endDay;

  public SessionPeriod(String startDay, String endDay) {
    this(LocalDate.parse(startDay), LocalDate.parse(endDay));
  }

  public SessionPeriod(LocalDate startDay, LocalDate endDay) {
    validate(startDay, endDay);
    this.startDay = startDay;
    this.endDay = endDay;
  }

  private void validate(LocalDate startDay, LocalDate endDay) {
    if (startDay.isAfter(endDay)) {
      throw new IllegalArgumentException("시작일은 종료일보다 이전이어야 합니다.");
    }
  }
}
