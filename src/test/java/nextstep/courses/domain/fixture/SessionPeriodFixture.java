package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.SessionPeriod;

import java.time.LocalDateTime;

public class SessionPeriodFixture {

    public static final LocalDateTime SESSION_START_AT = LocalDateTime.now().plusDays(10);
    public static final LocalDateTime SESSION_END_AT = SESSION_START_AT.plusMonths(2);

    public static SessionPeriod period(LocalDateTime startAt, LocalDateTime endAt) {
        return new SessionPeriod(startAt, endAt);
    }

    public static SessionPeriod period() {
        return new SessionPeriod(SESSION_START_AT, SESSION_END_AT);
    }

}
