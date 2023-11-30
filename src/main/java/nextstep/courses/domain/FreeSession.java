package nextstep.courses.domain;

import java.time.LocalDateTime;

public class FreeSession extends Session {
    public FreeSession(final String title, final LocalDateTime startDate, final LocalDateTime endDate) {
        super(title, startDate, endDate);
    }
}
