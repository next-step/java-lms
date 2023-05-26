package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.Period;

public class LecturePeriod {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public LecturePeriod(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }

    public int calculateLectureDuration() {
        Period period = Period.between(startDate, endDate);
        return period.getDays();
    }
}
