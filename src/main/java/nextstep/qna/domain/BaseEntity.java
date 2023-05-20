package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class BaseEntity {

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

}
