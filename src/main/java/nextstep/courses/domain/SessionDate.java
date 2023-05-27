package nextstep.courses.domain;

import java.time.LocalDate;
import java.util.Objects;

public class SessionDate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public SessionDate(LocalDate startDate, LocalDate endDate) {
        if(startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("수강 시작일(" + startDate + ")은 수강 종료일(" + endDate + ") 이후일 수 없습니다.");
        }

        this.startDate = startDate;
        this.endDate = endDate;
    }

    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof SessionDate)) return false;
        SessionDate that = (SessionDate) o;
        return Objects.equals(startDate, that.startDate) && Objects.equals(endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }
}
