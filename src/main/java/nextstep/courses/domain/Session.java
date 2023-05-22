package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public Session(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
