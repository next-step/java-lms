package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class SoftDeleteAbleClass {

    private boolean deleted = false;

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    boolean isDeleted() {
        return this.deleted;
    }

    void updateDeleted() {
        this.deleted = true;
    }
}
