package nextstep.courses.domain;

import java.time.LocalDateTime;

public class Session {
    private final Long id;
    private final LocalDateTime startDate;
    private final LocalDateTime endDate;
    private final LocalDateTime createdAt;
    private final LocalDateTime updatedAt;

    public Session(LocalDateTime startDate, LocalDateTime endDate) {
        this.id = 0L;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }

    public Session(Long id, LocalDateTime startDate, LocalDateTime endDate) {
        this.id = id;
        this.startDate = startDate;
        this.endDate = endDate;
        this.createdAt = LocalDateTime.now();
        this.updatedAt = LocalDateTime.now();
    }
}
