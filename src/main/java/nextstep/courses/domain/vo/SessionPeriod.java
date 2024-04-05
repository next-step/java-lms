package nextstep.courses.domain.vo;

import java.time.LocalDate;

public class SessionPeriod {

    private LocalDate start;
    private LocalDate end;


    public SessionPeriod(LocalDate start, LocalDate end) {
        if (start.isAfter(end)) {
            throw new IllegalArgumentException("start should be before end");
        }
        this.start = start;
        this.end = end;
    }
}
