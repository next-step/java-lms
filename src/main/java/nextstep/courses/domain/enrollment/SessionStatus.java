package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionStatusCannotEnrollmentException;

public enum SessionStatus {

    PREPARING("진행중", false),
    RECRUITING("모집중", true),
    FINISHED("종료", false)
    ;

    private final String status;

    private final boolean canEnroll;

    SessionStatus(String status, boolean canEnroll) {
        this.status = status;
        this.canEnroll = canEnroll;
    }

    public void canEnroll() {
        if (!this.canEnroll) {
            throw new SessionStatusCannotEnrollmentException(this);
        }
    }

    public String get() {
        return status;
    }

}
