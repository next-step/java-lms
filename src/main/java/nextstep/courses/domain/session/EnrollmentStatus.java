package nextstep.courses.domain.session;

public enum EnrollmentStatus {
    WAITING, APPROVED, REJECTED;

    public boolean isApproved() {
        return this == APPROVED;
    }
}
