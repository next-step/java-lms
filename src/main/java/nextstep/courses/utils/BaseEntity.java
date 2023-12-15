package nextstep.courses.utils;

import java.time.LocalDateTime;

public class BaseEntity {
    public LocalDateTime createdAt;

    public LocalDateTime updatedAt;

    public LocalDateTime createdAt() {
        return createdAt;
    }

    public LocalDateTime updatedAt() {
        return updatedAt;
    }
}
