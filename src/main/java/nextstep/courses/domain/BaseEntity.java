package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    public BaseEntity() {
        createdAt = LocalDateTime.now();
        updatedAt = LocalDateTime.now();
    }
}
