package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {

    private LocalDateTime startDate;
    private LocalDateTime endDate;
    private Image coverImage;

    public Session() {
    }

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.startDate = startDate;
        this.endDate = endDate;
    }
}
