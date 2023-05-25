package nextstep.courses.domain;

public enum EnrollmentStatus {
    DISABLE,
    ENABLE;

    public boolean isEnrolling() {
        return this == ENABLE;
    }
}
