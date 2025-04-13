package nextstep.courses.domain;

public enum SessionStatus {
    READY,
    ENROLLING,
    COMPLETED;

    public boolean canEnroll() {
        return this == ENROLLING;
    }
}
