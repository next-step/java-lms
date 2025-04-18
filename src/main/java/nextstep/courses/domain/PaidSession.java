package nextstep.courses.domain;

import java.math.BigDecimal;
import java.time.LocalDate;

public class PaidSession extends Session {
    private final MaxAttendeeCount maxAttendees;
    private final Amount amount;

    protected PaidSession(LocalDate startAt, LocalDate endAt, CoverImage coverImage, Integer maxAttendeeCount, BigDecimal amount) {
        super(startAt, endAt, coverImage);
        this.maxAttendees = new MaxAttendeeCount(maxAttendeeCount);
        this.amount = new Amount(amount);
    }
}
