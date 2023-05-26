package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {

    protected LocalDateTime createdDate = LocalDateTime.now();

    protected LocalDateTime updatedDate;

}
