package nextstep.courses.domain.session;

import nextstep.courses.exception.CannotEnrollmentSessionStatusException;

import java.time.LocalDateTime;

public enum SessionStatus {

    PREPARING(false),
    RECRUITING(true),
    FINISHED(false)
    ;

    private Long id;
    private final boolean canEnroll;
    private LocalDateTime createdAt;
    private LocalDateTime updatedAt;


    SessionStatus(boolean canEnroll) {
        this.canEnroll = canEnroll;
    }

    public void validateCanEnrollment() throws CannotEnrollmentSessionStatusException {
        if (!this.canEnroll) {
            throw new CannotEnrollmentSessionStatusException(this);
        }
    }

}
