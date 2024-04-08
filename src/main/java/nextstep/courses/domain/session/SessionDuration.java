package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionDuration {

    private final Long id;
    private final Long sessionId;
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SessionDuration(Long id, Long sessionId, LocalDateTime startAt, LocalDateTime endAt) {
        this.id = id;
        this.sessionId = sessionId;
        this.startAt = startAt;
        this.endAt = endAt;
    }

}
