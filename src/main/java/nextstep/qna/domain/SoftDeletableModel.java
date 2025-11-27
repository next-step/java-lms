package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class SoftDeletableModel {

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    private boolean deleted = false;

    protected void deleted(){
        this.deleted = true;
    }

    protected boolean getDeleted() {
        return deleted;
    }
}
