package nextstep.courses.domain;

import java.time.LocalDate;

public enum SessionDateFixture {
    TDD_SESSION_DATE(LocalDate.of(2023, 4, 3), LocalDate.of(2023, 6, 1));

    private final LocalDate startDate;
    private final LocalDate endDate;

    SessionDateFixture(LocalDate startDate, LocalDate endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }

    public SessionDate sessionDate() {
        return new SessionDate(startDate, endDate);
    }
}
