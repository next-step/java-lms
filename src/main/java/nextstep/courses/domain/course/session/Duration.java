package nextstep.courses.domain.course.session;

import java.time.LocalDate;
import java.util.Objects;

public class Duration {
    private LocalDate startDate;

    private LocalDate endDate;

    public Duration(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate(LocalDate startDate, LocalDate endDate) {
        checkStartDateOrEndDateNull(startDate, endDate);
        checkDurationIsValid(startDate, endDate);
    }

    private void checkStartDateOrEndDateNull(LocalDate startDate, LocalDate endDate) {
        if (startDate == null || endDate == null) {
            throw new IllegalArgumentException("시작 날짜와 종료 일자를 모두 입력해주세요.");
        }
    }

    private void checkDurationIsValid(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("시작 날짜가 종료 날짜보다 늦을 수 없습니다.");
        }
    }

    public boolean startDateIsSameOrBefore(LocalDate date) {
        return this.startDate == date || this.startDate.isBefore(date);
    }

    public boolean endDateIsSameOrAfter(LocalDate date) {
        return this.endDate == date || this.endDate.isAfter(date);
    }

    public LocalDate getStartDate() {
        return startDate;
    }

    public LocalDate getEndDate() {
        return endDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Duration duration = (Duration) o;
        return Objects.equals(startDate, duration.startDate) && Objects.equals(endDate, duration.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(startDate, endDate);
    }

    @Override
    public String toString() {
        return "Duration{" +
                "startDate=" + startDate +
                ", endDate=" + endDate +
                '}';
    }
}
