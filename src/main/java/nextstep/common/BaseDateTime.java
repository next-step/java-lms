package nextstep.common;

import java.time.LocalDateTime;

public abstract class BaseDateTime {

    private final LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    protected BaseDateTime() {
        this(LocalDateTime.now(), LocalDateTime.now());
    }

    protected BaseDateTime(final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public LocalDateTime createdAt() {
        return this.createdAt;
    }

    public LocalDateTime updatedAt() {
        return this.updatedAt;
    }

    public void updateDateTime() {
        this.updatedAt = LocalDateTime.now();
    }
}
