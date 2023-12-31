package nextstep.common.Builder;

import nextstep.common.Period;

import java.time.LocalDate;

public class PeriodBuilder {
    private LocalDate startAt = LocalDate.now();
    private LocalDate endAt = LocalDate.now().plusDays(15);

    public PeriodBuilder withStartAt(LocalDate startAt) {
        this.startAt = startAt;
        return this;
    }

    public PeriodBuilder withEndAt(LocalDate endAt) {
        this.endAt = endAt;
        return this;
    }

    public Period build() {
        return new Period(startAt, endAt);
    }
}
