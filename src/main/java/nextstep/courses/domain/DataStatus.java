package nextstep.courses.domain;

import nextstep.courses.util.DateTimeGenerator;

import java.time.LocalDateTime;

public class DataStatus {
    private boolean deleted;

    private Long creatorId;

    private LocalDateTime createdAt;

    private LocalDateTime updatedAt;

    public DataStatus(Long creatorId) {
        this(false, creatorId, DateTimeGenerator.generateNowDateTime(), null);
    }

    public DataStatus(boolean deleted,
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
        this.updatedAt = DateTimeGenerator.generateNowDateTime();
    }
}
