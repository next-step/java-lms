package nextstep.qna.domain;

import java.time.LocalDateTime;

public class Times {

    private final LocalDateTime createdAt;

    private final LocalDateTime updatedAt;

    public Times(LocalDateTime createdAt, LocalDateTime updatedAt) {
        this.createdAt = createdAt;
        this.updatedAt = updatedAt;
    }
}

