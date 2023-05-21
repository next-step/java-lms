package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class BaseTimeEntity {
    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;
}
