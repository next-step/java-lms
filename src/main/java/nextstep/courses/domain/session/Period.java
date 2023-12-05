package nextstep.courses.domain.session;

import nextstep.courses.exception.session.InvalidDateRangeException;

import java.time.LocalDate;

public class Period {
    private final LocalDate startDate;
    private final LocalDate endDate;

    private Period(LocalDate startDate, LocalDate endDate) {
        validate(startDate, endDate);

        this.startDate = startDate;
        this.endDate = endDate;
    }

    private void validate(LocalDate startDate, LocalDate endDate) {
        if (startDate.isAfter(endDate)) {
            throw new InvalidDateRangeException("시작일은 종료일 보다 이전이어야 합니다.");
        }
    }

    public static Period from() {
        return new Period(LocalDate.now(), LocalDate.now().plusDays(7));
    }

    public static Period of(LocalDate startDate, LocalDate endDate) {
        return new Period(startDate, endDate);
    }


    public LocalDate startDate() {
        return startDate;
    }

    public LocalDate endDate() {
        return endDate;
    }
}
