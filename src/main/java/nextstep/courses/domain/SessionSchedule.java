package nextstep.courses.domain;

import java.time.LocalDateTime;

public class SessionSchedule {

    private LocalDateTime startAt;

    private LocalDateTime endAt;

    public SessionSchedule() {
    }

    public SessionSchedule(LocalDateTime startAt, LocalDateTime endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }
}
