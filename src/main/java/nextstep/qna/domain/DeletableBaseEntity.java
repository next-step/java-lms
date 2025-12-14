package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class DeletableBaseEntity {

    private Long id;
    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    protected DeletableBaseEntity() {
    }

    public DeletableBaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }

    protected void delete() {
        this.deleted = true;
    }

    public boolean isDeleted() {
        return deleted;
    }
}
