package nextstep.courses.domain;

import java.time.LocalDate;

/**
 * 강의 엔터티
 */
public class Session {
    private final Long id;
    private final Period period;

    public Session(Long id, Period period) {
        this.id = id;
        this.period = period;
    }

    public LocalDate startAt() {
        return period.startAt();
    }

    public LocalDate endAt() {
        return period.endAt();
    }
}
