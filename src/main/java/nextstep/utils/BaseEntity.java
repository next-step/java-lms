package nextstep.utils;

import java.time.LocalDateTime;

public class BaseEntity {

    private long id;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    public BaseEntity(long id, LocalDateTime startedAt, LocalDateTime endedAt) {
        this.id = id;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

}
