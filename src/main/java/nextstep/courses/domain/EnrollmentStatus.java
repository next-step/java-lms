package nextstep.courses.domain;

public enum EnrollmentStatus {

    PENDING, APPROVED, REJECTED;

    public boolean isSame(EnrollmentStatus enrollmentStatus) {
        return this == enrollmentStatus;
    }
}
