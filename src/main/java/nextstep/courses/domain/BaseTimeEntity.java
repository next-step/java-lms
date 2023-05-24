package nextstep.courses.domain;

import java.time.LocalDateTime;

public abstract class BaseTimeEntity {
    protected final LocalDateTime createdAt;
    protected final LocalDateTime updatedAt;

    public BaseTimeEntity(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }

   public BaseTimeEntity() {
       this(LocalDateTime.now(), LocalDateTime.now());
   }
}
