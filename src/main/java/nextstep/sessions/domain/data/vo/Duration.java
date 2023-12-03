package nextstep.sessions.domain.data.vo;

import java.time.LocalDateTime;

public class Duration {

    private final LocalDateTime startDate;
    private final LocalDateTime endDate;

    public Duration(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
