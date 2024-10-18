package nextstep.qna.domain;

import java.time.LocalDateTime;

public class Auditable {
    private final LocalDateTime createdDate = LocalDateTime.now();

    private LocalDateTime updatedDate;

}
