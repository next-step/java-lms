package nextstep.courses.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaidSession extends Session {
    private final Integer maxAttendees;
    private final BigDecimal amount;

    protected PaidSession(LocalDate startAt, LocalDate endAt, CoverImage coverImage, Integer maxAttendees, BigDecimal amount) {
        super(startAt, endAt, coverImage);
        this.maxAttendees = maxAttendees;
        this.amount = amount;
    }
}
