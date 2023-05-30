package nextstep.courses.domain;

import java.time.LocalDateTime;

public class DateTime {

    private final LocalDateTime startAt;
    private final LocalDateTime endAt;

    public DateTime(LocalDateTime startAt, LocalDateTime endAt) {
        if (startAt.isAfter(endAt)) {
            throw new IllegalArgumentException("시작일은 종료일 이후일 수 없습니다.");
        }
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public LocalDateTime startAt() {
        return startAt;
    }

    public LocalDateTime endAt() {
        return endAt;
    }
}
