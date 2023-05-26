package nextstep.Fixtures;

import nextstep.courses.domain.SessionBillingType;
import nextstep.courses.domain.SessionBuilder;
import nextstep.courses.domain.SessionPeriod;

import java.time.LocalDateTime;

public class SessionFixtures {
    public static SessionBuilder testSession1() {
        return SessionBuilder.init()
                .sessionPeriod(new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)))
                .sessionBillType(SessionBillingType.FREE)
                .id(1L);
    }
}
