package nextstep.qna.domain;

import java.time.LocalDateTime;

public class BaseEntity {
    private LocalDateTime createdDate = LocalDateTime.now();
    private LocalDateTime updatedDate;
}
