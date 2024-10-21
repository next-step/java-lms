package nextstep.qna.domain.common;

import java.time.LocalDateTime;

public abstract class BaseTime {

    private LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;
}
