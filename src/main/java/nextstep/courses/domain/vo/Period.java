package nextstep.courses.domain.vo;

import java.time.LocalDate;

public class Period {

    private LocalDate start;
    private LocalDate end;

    public Period(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("start should before end date");
        }
        this.start = start;
        this.end = end;
    }
}
