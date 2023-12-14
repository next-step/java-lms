package nextstep.courses.domain.session;

import nextstep.courses.NotEnrollmentPeriodException;

import java.time.LocalDate;

public class Schedule {

    private final LocalDate startDate;

    private final LocalDate endDate;

    public Schedule() {
        this(LocalDate.now(), LocalDate.now());
    }

    public Schedule(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }


    public final boolean isEnrollmentPeriod() {
        return SessionStatus.status(this).equals(SessionStatus.ENROLLING);
    }

    public final boolean isBeforeStartDate(LocalDate date) {
        return date.isBefore(this.startDate);
    }

    public final boolean isAfterEndDate(LocalDate date) {
        return date.isAfter(this.endDate);
    }
}
