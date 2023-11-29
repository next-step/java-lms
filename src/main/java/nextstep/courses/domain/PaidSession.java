package nextstep.courses.domain;

import java.time.LocalDateTime;

public class PaidSession extends Session {
    protected PaidSession(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, startDate, endDate);
    }
}
