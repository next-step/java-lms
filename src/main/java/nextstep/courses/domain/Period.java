package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Period {
    private LocalDateTime startDate;

    private LocalDateTime endDate;

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("종료일이 시작일보다 빠를 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
