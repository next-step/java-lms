package nextstep.courses.domain;

import java.time.LocalDate;

public class Session {
    private LocalDate startDate;

    private LocalDate endDate;

    public Session() {
        this(LocalDate.now(), null);
    }

    public Session(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
