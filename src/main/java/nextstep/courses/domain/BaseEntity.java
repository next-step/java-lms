package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    private Long id;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;

    public BaseEntity(final Long id, final LocalDateTime createdAt, final LocalDateTime updatedAt) {
        this.id = id;
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}
