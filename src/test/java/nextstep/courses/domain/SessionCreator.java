package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionCreator {
    public static Session create(Long maxNumOfStudent, SessionStatus status) {
        SessionInfo sessionInfo = new SessionInfo(1L,0L, "titl1", "img", SessionType.FREE);
        SessionTimeLine sessionTimeLine = new SessionTimeLine(LocalDateTime.now(), LocalDateTime.now().plusDays(10));
        return new Session(sessionInfo, status, sessionTimeLine, maxNumOfStudent);
    }
}
