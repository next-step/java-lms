package nextstep.qna.domain;

import java.time.LocalDateTime;

public class TimeStamp {
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public TimeStamp() {
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    private TimeStamp(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
