package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public class SessionAdditionalInformation {

    private final LocalDateTime created;

    private LocalDateTime updated;

    public SessionAdditionalInformation(LocalDateTime created) {
        this.created = created;
    }
}
