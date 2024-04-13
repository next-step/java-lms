package nextstep.courses.domain.fixture;

import nextstep.courses.domain.enrollment.SessionPeriod;

import java.time.LocalDateTime;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_DURATION_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;

public class SessionPeriodFixture {

    public static final LocalDateTime SESSION_START_AT = LocalDateTime.now().plusDays(10);
    public static final LocalDateTime SESSION_END_AT = SESSION_START_AT.plusMonths(2);

    public static SessionPeriod sessionDuration(LocalDateTime startAt, LocalDateTime endAt) {
        return new SessionPeriod(SESSION_DURATION_ID, SESSION_ID, startAt, endAt);
    }

}
