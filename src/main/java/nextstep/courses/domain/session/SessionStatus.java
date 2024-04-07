package nextstep.courses.domain.session;

import java.time.LocalDateTime;

public enum SessionStatus {

    PREPARING(true),
    IN_PROGRESS(false),
    FINISHED(false)
    ;

    private Long id;
    private final boolean canEnroll;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    SessionStatus(boolean canEnroll) {
        this.canEnroll = canEnroll;
    }

    public boolean canEnroll() {
        return this.canEnroll;
    }

}
