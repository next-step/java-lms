package nextstep.courses.domain.session.metadata;

import java.time.LocalDate;

import nextstep.courses.InvalidPeriodException;

public class Period {
    private final LocalDate startAt;
    private final LocalDate endAt;

    public Period(LocalDate startAt, LocalDate endAt) {
        if (startAt.isAfter(endAt)) {
            throw new InvalidPeriodException("시작일은 종료일보다 늦을 수 없습니다.");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDate startAt() {
        return startAt;
    }

    public LocalDate endAt() {
        return endAt;
    }
}
