package nextstep.qna.domain;

import java.time.LocalDateTime;

public class BaseEntity {

    private LocalDateTime createdAt = LocalDateTime.now();
    private LocalDateTime updatedAt;

    protected void update() {
        this.updatedAt = LocalDateTime.now();
    }
}
