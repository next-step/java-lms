package nextstep.courses.domain.session;

import java.time.LocalDate;
import java.util.Objects;

public class Schedule {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Schedule(final LocalDate startDate, final LocalDate endDate) {
        validateSessionScheduleEndDateIsAfterStartDate(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validateSessionScheduleEndDateIsAfterStartDate(final LocalDate startDate, final LocalDate endDate) {
        if (endDate.isBefore(startDate)) {
            throw new IllegalArgumentException("강의 종료일은 시작일 보다 앞설 수 없습니다.");
        }
    }

    public LocalDate startDate() {
        return this.startDate;
    }

    public LocalDate endDate() {
        return this.endDate;
    }

    @Override
    public boolean equals(final Object otherSchedule) {
        if (this == otherSchedule) {
            return true;
        }

        if (otherSchedule == null || getClass() != otherSchedule.getClass()) {
            return false;
        }

        final Schedule that = (Schedule)otherSchedule;

        return Objects.equals(this.startDate, that.startDate) &&
                Objects.equals(this.endDate, that.endDate);
    }

    @Override
    public int hashCode() {
        return Objects.hash(this.startDate, this.endDate);
    }
}
