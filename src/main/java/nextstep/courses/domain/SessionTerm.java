package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionTerm {

    private final LocalDateTime startAt;

    private final LocalDateTime endAt;

    public SessionTerm(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
