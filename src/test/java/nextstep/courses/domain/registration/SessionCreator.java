package nextstep.courses.domain.registration;

import nextstep.courses.domain.Session;
import nextstep.courses.domain.SessionInfo;
import nextstep.courses.domain.SessionPeriod;
import nextstep.courses.domain.SessionType;

import java.time.LocalDateTime;

public class SessionCreator {

    public static Session create(Long maxNumberOfStudent, SessionStatus sessionStatus) {
        SessionInfo sessionInfo = new SessionInfo(1L, 0L, "titleName",
                "coverImage", SessionType.FREE);
        SessionRegistration sessionRegistration = new SessionRegistration(sessionStatus, maxNumberOfStudent);
        SessionPeriod sessionPeriod = new SessionPeriod(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        return new Session(sessionInfo, sessionRegistration, sessionPeriod);
    }
}
