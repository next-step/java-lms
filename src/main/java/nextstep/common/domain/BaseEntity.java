package nextstep.common.domain;

import java.time.LocalDateTime;

public class BaseEntity {

    private final LocalDateTime createdAt;
    private LocalDateTime lastModifiedAt;
    private boolean deleted = false;

    public BaseEntity() {
        this(LocalDateTime.now(), LocalDateTime.now());
    }

    public BaseEntity(LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this(false, createdAt, lastModifiedAt);
    }

    public BaseEntity(boolean deleted, LocalDateTime createdAt, LocalDateTime lastModifiedAt) {
        this.deleted = deleted;
        this.createdAt = createdAt;
        this.lastModifiedAt = lastModifiedAt;
    }

    public void delete(LocalDateTime modifiedAt) {
        this.deleted = true;
        modified(modifiedAt);
    }

    private void modified(LocalDateTime modifiedAt) {
        this.lastModifiedAt = modifiedAt;
    }

    public boolean isDeleted() {
        return this.deleted;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public LocalDateTime getLastModifiedAt() {
        return lastModifiedAt;
    }
}
