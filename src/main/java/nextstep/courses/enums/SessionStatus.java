package nextstep.courses.enums;

public enum SessionStatus {
    PREPARING,
    ENROLLING,
    FINISH;

    public boolean isEnrolling() {
        return this == ENROLLING;
    }

}
