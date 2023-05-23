package nextstep.qna.domain.base;

import java.time.LocalDateTime;

public class BaseDate extends CreatedDate {

    protected LocalDateTime updatedDate;

    public BaseDate() {
    }

    public BaseDate(final LocalDateTime updatedDate) {
        this.updatedDate = updatedDate;
    }

    public BaseDate(final LocalDateTime createdDate, final LocalDateTime updatedDate) {
        super(createdDate);
        this.updatedDate = updatedDate;
    }

}
