package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseTime {

    protected final LocalDateTime createdAt;

    protected final LocalDateTime updatedAt;

    public BaseTime(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public BaseTime() {
        this(LocalDateTime.now(), LocalDateTime.now());
    }
}
