package nextstep.courses.domain.session.period;

import java.time.LocalDate;

public class Period {

    private LocalDate startDate;
    private LocalDate endDate;

    public Period(LocalDate startDate, LocalDate endDate) {
        validatePeriod(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validatePeriod(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new IllegalArgumentException(String.format("강의 시작일은 강의 종료일보다 앞서야 합니다. 시작일 : %s, 종료일 : %s", startDate, endDate));
        }
    }

    public static Period from(LocalDate startDate, LocalDate endDate) {
        return new Period(startDate, endDate);
    }
}
