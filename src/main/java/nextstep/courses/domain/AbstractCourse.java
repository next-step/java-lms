package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class AbstractCourse {
    protected Long id;

    protected String title;

    protected Long creatorId;

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    public Long getId() {
        return id;
    }
}
