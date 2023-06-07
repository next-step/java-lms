package nextstep.fixtures;

import nextstep.courses.domain.*;

import java.time.LocalDateTime;

public class SessionFixtures {
    public static Session testSession1() {
        return new Session(1L, SessionBilling.FREE, SessionStatus.OPEN,"https://nextstep.tdd",10, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)), LocalDateTime.now() ,LocalDateTime.now());
    }

    public static Session testSession2() {
        return new Session(1L, SessionBilling.PAID, SessionStatus.READY,"https://nextstep.tdd", 10, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)) , LocalDateTime.now(),LocalDateTime.now() );
    }

    public static Session testSession3() {
        return new Session(1L, SessionBilling.FREE, SessionStatus.CLOSED,"https://nextstep.tdd",10, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)) , LocalDateTime.now() ,LocalDateTime.now());
    }

    public static Session testSession4() {
        return new Session(1L, SessionBilling.FREE, SessionStatus.CLOSED,"https://nextstep.tdd", 0, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)), LocalDateTime.now(), LocalDateTime.now());
    }

    public static Session testSession5() {
        return new Session(1L, SessionBilling.FREE, SessionStatus.OPEN,"https://nextstep.tdd", 0, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)), LocalDateTime.now(), LocalDateTime.now());
    }
}
