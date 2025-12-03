package nextstep.qna.domain;

import java.time.LocalDateTime;

public class TimeStamp {
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    public TimeStamp() {
        this.createdDate = LocalDateTime.now();
        this.updatedDate = LocalDateTime.now();
    }

    private TimeStamp(LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public void update() {
        this.updatedDate = LocalDateTime.now();
    }
}
