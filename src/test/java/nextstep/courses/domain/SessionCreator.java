package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionCreator {

    public static Session create(Long maxNumberOfStudent, SessionStatus sessionStatus) {
        SessionInfo sessionInfo = new SessionInfo(1L, 0L, "titleName",
                "coverImage", SessionType.FREE);
        SessionTimeLine sessionTimeLine = new SessionTimeLine(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        return new Session(sessionInfo, sessionStatus, sessionTimeLine, maxNumberOfStudent);
    }
}
