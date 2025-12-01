package nextstep.courses.domain.session.builder;

import nextstep.courses.domain.session.SessionRange;

import java.time.LocalDateTime;

public class SessionRangeBuilder {

    private LocalDateTime startDate = LocalDateTime.of(2025, 11, 1, 0, 0, 0);
    private LocalDateTime endDate = LocalDateTime.of(2025, 11, 30, 11, 59, 59);

    public SessionRangeBuilder withStartDate(LocalDateTime startDate) {
        this.startDate = startDate;
        return this;
    }

    public SessionRangeBuilder withEndDate(LocalDateTime endDate) {
        this.endDate = endDate;
        return this;
    }

    public SessionRange build() {
        return new SessionRange(startDate, endDate);
    }
}
