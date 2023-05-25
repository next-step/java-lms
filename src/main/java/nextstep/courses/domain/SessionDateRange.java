package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionDateRange {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionDateRange(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료일이 강의 시작일보다 빠릅니다");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
