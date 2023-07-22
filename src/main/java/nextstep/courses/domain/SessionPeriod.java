package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionPeriod {
    private LocalDate startDate;
    private LocalDate endDate;

    public SessionPeriod(LocalDate startDate, LocalDate endDate) throws IllegalArgumentException{
        if(endDate.compareTo(startDate) <= 0) {
            throw new IllegalArgumentException("기간을 확인해 주세요");
        }
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
