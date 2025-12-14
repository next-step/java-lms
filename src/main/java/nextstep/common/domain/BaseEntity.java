package nextstep.common.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdDate;
    private LocalDateTime updatedDate;

    protected BaseEntity(Long id) {
        this(id, LocalDateTime.now(), LocalDateTime.now());
    }

    public BaseEntity(Long id, LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.id = id;
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }

    public Long getId() {
        return id;
    }
}
