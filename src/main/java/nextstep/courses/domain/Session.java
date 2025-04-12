package nextstep.courses.domain;

import java.time.LocalDate;
import java.time.LocalDateTime;

public class Session {
    private final LocalDate startAt;
    private final LocalDate endAt;

    public Session(LocalDate startAt, LocalDate endAt) {
        this.startAt = startAt;
        this.endAt = endAt;
    }

    public Session() {
        this(LocalDate.now(), LocalDate.now());
    }
}
