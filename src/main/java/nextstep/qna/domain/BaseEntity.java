package nextstep.qna.domain;

import java.time.LocalDateTime;

public class BaseEntity {

    LocalDateTime createdDate = LocalDateTime.now();

    LocalDateTime updatedDate;
}
