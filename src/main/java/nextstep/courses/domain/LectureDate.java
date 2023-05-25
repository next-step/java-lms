package nextstep.courses.domain;

import java.time.LocalDate;

public class LectureDate {
    private final LocalDate startDate;
    private final LocalDate endDate;

    public LectureDate(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);
        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException("Start date must be before end date");
        }
    }
}
