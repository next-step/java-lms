package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료일은 시작일 이후이어야 합니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
