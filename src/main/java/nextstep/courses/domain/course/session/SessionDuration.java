package nextstep.courses.domain.course.session;

import java.time.LocalDate;
import java.util.Objects;

public class SessionDuration {
    private final LocalDate startDate;

    private final LocalDate endDate;

    public SessionDuration(LocalDate startDate, LocalDate endDate) {
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

    public boolean changeDateIsSameOrAfterWithEndDate(LocalDate date) {
        return date == this.endDate || date.isAfter(this.endDate);
    }

    public boolean changeDateIsBeforeOrSameWithEndDate(LocalDate date) {
        return date.isBefore(this.endDate) || date == this.endDate;
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
        if (o == null || getClass() != o.getClass()) return false;
        SessionDuration sessionDuration = (SessionDuration) o;
        return Objects.equals(startDate, sessionDuration.startDate) && Objects.equals(endDate, sessionDuration.endDate);
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
