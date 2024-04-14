package nextstep.utils;

import java.time.LocalDateTime;

public class BaseEntity {

    private long id;

    private LocalDateTime startedAt;

    private LocalDateTime endedAt;

    public BaseEntity(long id, LocalDateTime startedAt, LocalDateTime endedAt) {
        if (startedAt.isAfter(endedAt)) {
            throw new IllegalArgumentException("시작일은 종료일보다 앞서야 됩니다.");
        }
        this.id = id;
        this.startedAt = startedAt;
        this.endedAt = endedAt;
    }

}
