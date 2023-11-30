package nextstep.courses.domain;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    public PaidSession(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, startDate, endDate);
    }

    public PaidSession(final String title, final double price, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, price, startDate, endDate);
    }
}
