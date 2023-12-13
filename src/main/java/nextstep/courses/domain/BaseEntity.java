package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    LocalDateTime createdAt;

    LocalDateTime updatedAt;

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
