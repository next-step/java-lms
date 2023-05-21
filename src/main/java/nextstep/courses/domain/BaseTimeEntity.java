package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class BaseTimeEntity {
    protected LocalDateTime createdAt = LocalDateTime.now();
    protected LocalDateTime updatedAt;
}
