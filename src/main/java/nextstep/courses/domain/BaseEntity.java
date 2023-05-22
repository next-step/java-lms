package nextstep.courses.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    public LocalDateTime createdAt = LocalDateTime.now();

    public LocalDateTime updatedAt;
}
