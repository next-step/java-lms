package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    protected Long id;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;
}
