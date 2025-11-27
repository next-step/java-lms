package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {
    private Long id;
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;

    public BaseEntity() {}

    public BaseEntity(Long id) {
        this.id = id;
    }

    public Long getId() {
        return id;
    }
}
