package nextstep.courses.domain.field;

import java.time.LocalDateTime;

public class Period {
    private LocalDateTime startDate;
    private LocalDateTime endDate;

    public Period() {
    }

    public void start() {
        this.startDate = LocalDateTime.now();
    }

    public void end() {
        this.endDate = LocalDateTime.now();
    }

    public boolean isStarted() {
        return !this.startDate.equals(null);
    }

    public boolean isEnded() {
        return !this.endDate.equals(null);
    }
}
