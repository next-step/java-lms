package nextstep.sessions.domain.data.session;

import java.time.LocalDateTime;

public class OpenInfo {

    private final Duration duration;

    public OpenInfo(Duration duration) {
        this.duration = duration;
    }

    public LocalDateTime startDate() {
        return duration.startDate();
    }

    public LocalDateTime endDate() {
        return duration.endDate();
    }
}
