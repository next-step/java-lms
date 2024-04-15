package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseInfo {
    private boolean deleted;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public BaseInfo(Long creatorId) {
        this(false, creatorId, LocalDateTime.now(), null);
    }

    public BaseInfo(boolean deleted,
                    Long creatorId,
                    LocalDateTime createdAt,
                    LocalDateTime updatedAt) {
        this.deleted = deleted;
        this.creatorId = creatorId;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

    public void delete() {
        this.deleted = true;
        this.updatedAt = LocalDateTime.now();
    }
}
