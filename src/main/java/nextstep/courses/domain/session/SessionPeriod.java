package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionPeriod {
    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public SessionPeriod() {
        this(LocalDateTime.now(), LocalDateTime.MAX);
    }

    public SessionPeriod(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException("시작일은 종료일 이후 일 수 없습니다.");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public static SessionPeriod between(LocalDateTime startAt, LocalDateTime endAt) {
        return new SessionPeriod(startAt, endAt);
    }

    public LocalDateTime getStartAt() {
        return startAt;
    }

    public LocalDateTime getEndAt() {
        return endAt;
    }
}
