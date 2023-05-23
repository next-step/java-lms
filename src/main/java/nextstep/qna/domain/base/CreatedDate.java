package nextstep.qna.domain.base;

import java.time.LocalDateTime;

public class CreatedDate {

    protected LocalDateTime createdDate = LocalDateTime.now();

    public CreatedDate() {
    }

    public CreatedDate(final LocalDateTime createdDate) {
        this.createdDate = createdDate;
    }
}
