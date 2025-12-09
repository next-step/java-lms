package nextstep.courses.domain;

import nextstep.courses.domain.session.Period;

import java.time.LocalDate;

public class BaseEntity {
    private final Long id;
    private final String title;
    private final Period period;

    public BaseEntity(Long id, String title, LocalDate startDate, LocalDate endDate) {
        this(id, title, new Period(startDate, endDate));
    }

    public BaseEntity(Long id, String title, Period period) {
        this.id = id;
        this.title = title;
        this.period = period;
    }
}
