package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Period {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        if(endDate.isBefore(startDate)){
            throw new IllegalArgumentException("기간의 시작시간이 종료시간보다 늦으면 안됩니다.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public Period(LocalDateTime endDate) {
        this(LocalDateTime.now(), endDate);
    }

    public LocalDateTime getStartDate() {
        return startDate;
    }
}
