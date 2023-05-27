package nextstep.fixtures;

import nextstep.courses.domain.*;

import java.time.LocalDateTime;

public class SessionFixtures {
    public static Session testSession1() {
        return new Session(10,1L,SessionBillingType.FREE, SessionStatus.OPEN,"https://nextstep.tdd", new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)));
    }

    public static Session testSession2() {
        return new Session(10,1L,SessionBillingType.PAID, SessionStatus.READY,"https://nextstep.tdd", new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)));
    }

    public static Session testSession3() {
        return new Session(10,1L,SessionBillingType.FREE, SessionStatus.CLOSED,"https://nextstep.tdd", new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)));
    }

    public static Session testSession4() {
        return new Session(0,1L,SessionBillingType.FREE, SessionStatus.CLOSED,"https://nextstep.tdd", new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)));
    }
}
