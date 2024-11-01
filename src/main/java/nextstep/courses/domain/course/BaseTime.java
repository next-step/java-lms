package nextstep.courses.domain.course;

import java.time.LocalDateTime;

public abstract class BaseTime {

    protected LocalDateTime createdAt;

    protected LocalDateTime updatedAt;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

}
