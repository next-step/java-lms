package nextstep.qna.domain;

import java.time.LocalDateTime;

public class DateRecord {

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

    public DateRecord(LocalDateTime createdDate, LocalDateTime updatedDate) {
        this.createdDate = createdDate;
        this.updatedDate = updatedDate;
    }
}
