package nextstep.core.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    private Long id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;

    protected BaseEntity() {}

    protected BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
