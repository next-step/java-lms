package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private boolean deleted = false;

    public BaseEntity() {}

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    public void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
