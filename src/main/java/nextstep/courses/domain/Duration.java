package nextstep.courses.domain;

import java.time.LocalDate;

public class Duration {

    private final LocalDate startDate;
    private final LocalDate endDate;

    public Duration() {
        this(LocalDate.now(), LocalDate.now().plusDays(60));
    }

    public Duration(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
