package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Period {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Period() {
        this(LocalDateTime.now(), LocalDateTime.now().plusWeeks(1));
    }

    public Period(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
