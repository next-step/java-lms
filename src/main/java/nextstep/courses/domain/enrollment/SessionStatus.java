package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionStatusCannotEnrollmentException;

public enum SessionStatus {

    PREPARING(false),
    RECRUITING(true),
    FINISHED(false)
    ;

    private final boolean canEnroll;

    SessionStatus(boolean canEnroll) {
        this.canEnroll = canEnroll;
    }

    public void canEnroll() {
        if (!this.canEnroll) {
            throw new SessionStatusCannotEnrollmentException(this);
        }
    }

}
