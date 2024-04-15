package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING,
    ENROLLING,
    CLOSED;

    public boolean isEnrolling() {
        return this == ENROLLING;
    }
}
