package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Period {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("강의 시작일은 종료일 이후일 수 없습니다.");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }

    public LocalDateTime getEndDate() {
        return endDate;
    }
}
