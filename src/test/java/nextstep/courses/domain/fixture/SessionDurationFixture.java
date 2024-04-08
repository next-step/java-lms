package nextstep.courses.domain.fixture;

import nextstep.courses.domain.session.SessionDuration;

import java.time.LocalDateTime;

import static nextstep.courses.domain.fixture.IdFixture.SESSION_DURATION_ID;
import static nextstep.courses.domain.fixture.IdFixture.SESSION_ID;

public class SessionDurationFixture {

    private static final LocalDateTime SESSION_START_AT = LocalDateTime.now().plusDays(10);
    private static final LocalDateTime SESSION_END_AT = SESSION_START_AT.plusMonths(2);

    public static SessionDuration sessionDuration() {
        return new SessionDuration(SESSION_DURATION_ID, SESSION_ID, SESSION_START_AT, SESSION_END_AT);
    }

}
