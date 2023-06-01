package nextstep.courses.domain;

import java.time.LocalDate;

public class SessionDate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
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

    @Override
    public String toString() {
        return "SessionDate{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
