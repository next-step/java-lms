package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionPeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료일이 강의 시작일보다 빠릅니다");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
