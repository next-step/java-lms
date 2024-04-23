package nextstep.sessions.domain;

import java.time.LocalDate;

public class StartedAt {

    private final LocalDate startedAt;

    public StartedAt(LocalDate startedAt) {
        this.startedAt = startedAt;
    }

    public LocalDate getStartedAt() {
        return startedAt;
    }
}
