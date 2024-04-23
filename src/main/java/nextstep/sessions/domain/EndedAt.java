package nextstep.sessions.domain;

import java.time.LocalDate;

public class EndedAt {

    private final LocalDate endedAt;

    public EndedAt(LocalDate endedAt) {
        this.endedAt = endedAt;
    }

    public LocalDate getEndedAt() {
        return endedAt;
    }
}
