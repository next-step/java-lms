package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionDuration {

    private final Session session;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;


    public SessionDuration(Session session, LocalDateTime startAt, LocalDateTime endAt) {
        this.session = session;
        this.startAt = startAt;
        this.endAt = endAt;
    }


}
