package nextstep.session.domain;

import java.time.LocalDateTime;
import nextstep.session.CannotMakeSession;

public class SessionDate {

  private LocalDateTime startDate;

  private LocalDateTime endDate;

  public SessionDate(LocalDateTime startDate, LocalDateTime endDate) {
    if (startDate.isAfter(endDate)) {
      throw new CannotMakeSession("강의 시작일은 강의 종료일보다 늦을 수 없습니다.");
    }
    this.startDate = startDate;
    this.endDate = endDate;
  }
}
