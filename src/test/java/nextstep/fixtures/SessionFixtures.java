package nextstep.fixtures;

import nextstep.courses.domain.*;

import java.time.LocalDateTime;

public class SessionFixtures {
    public static Session testSession1() {
        return new Session(1L, SessionBilling.FREE, "https://nextstep.tdd", SessionStatus.OPEN, SessionRecruitStatus.RECRUIT ,10, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)), LocalDateTime.now() ,LocalDateTime.now());
    }

    public static Session testSession2() {
        return new Session(1L, SessionBilling.PAID,"https://nextstep.tdd", SessionStatus.READY, SessionRecruitStatus.NOT_RECRUIT,10, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)) , LocalDateTime.now(),LocalDateTime.now() );
    }

    public static Session testSession3() {
        return new Session(1L, SessionBilling.FREE,"https://nextstep.tdd",SessionStatus.CLOSED, SessionRecruitStatus.NOT_RECRUIT,10, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)) , LocalDateTime.now() ,LocalDateTime.now());
    }

    public static Session testSession4() {
        return new Session(1L, SessionBilling.FREE, "https://nextstep.tdd", SessionStatus.CLOSED, SessionRecruitStatus.NOT_RECRUIT,0,new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)), LocalDateTime.now(), LocalDateTime.now());
    }

    public static Session testSession5() {
        return new Session(1L, SessionBilling.FREE,"https://nextstep.tdd", SessionStatus.OPEN, SessionRecruitStatus.RECRUIT ,0, new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(7)), LocalDateTime.now(), LocalDateTime.now());
    }
}
