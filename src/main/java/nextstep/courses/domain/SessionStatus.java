package nextstep.courses.domain;

public enum SessionStatus {
   PREPARING,
    ENROLLING;

    public boolean isEnrolling() {
        return this == ENROLLING;
    }
}
