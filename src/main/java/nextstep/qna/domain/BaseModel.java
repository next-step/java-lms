package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class BaseModel {

    public LocalDateTime createdDate = LocalDateTime.now();

    public LocalDateTime updatedDate;

    public boolean deleted = false;
}
