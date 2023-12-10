package nextstep.courses.domain.session;

import java.time.LocalDate;

public class Session {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Session(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
