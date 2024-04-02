package nextstep.qna.domain;

import java.time.LocalDateTime;

public abstract class BaseTime {
  protected final LocalDateTime createdDate = LocalDateTime.now();
  protected LocalDateTime updatedDate;
}
