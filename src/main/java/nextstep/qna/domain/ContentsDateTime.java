package nextstep.qna.domain;

import java.time.LocalDateTime;

public class ContentsDateTime {

    private final LocalDateTime createdDate = LocalDateTime.now();

    private final LocalDateTime updatedDate;

    public ContentsDateTime(LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public LocalDateTime getCreatedDate() {
        return createdDate;
    }

    public LocalDateTime getUpdatedDate() {
        return updatedDate;
    }
}
