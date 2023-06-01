package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionOptional {

    private final LocalDateTime created;

    private LocalDateTime updated;

    public SessionOptional(LocalDateTime created) {
        this.created = created;
    }

    public SessionOptional(LocalDateTime created, LocalDateTime updated) {
        this.created = created;
        this.updated = updated;
    }
}
