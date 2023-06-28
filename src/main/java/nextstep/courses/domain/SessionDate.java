package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionDate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        validDate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validDate(LocalDate startDate, LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new RuntimeException("날짜가 올바르지 않습니다.");
        }
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }
}
