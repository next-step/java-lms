package nextstep.qna.domain.session;

import java.time.LocalDateTime;

public class SessionPeriod {
    private LocalDateTime startDateTime;
    private LocalDateTime endDateTime;

    public static SessionPeriod of(String startDateTime, String endDateTime) {
        SessionPeriod sessionPeriod = new SessionPeriod();
        sessionPeriod.startDateTime = LocalDateTime.parse(startDateTime);
        sessionPeriod.endDateTime = LocalDateTime.parse(endDateTime);
        return sessionPeriod;
    }
}
