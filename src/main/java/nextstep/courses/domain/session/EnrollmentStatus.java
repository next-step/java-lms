package nextstep.courses.domain.session;

public enum EnrollmentStatus {
    OPEN,
    CLOSED;

    public void validateEnroll() {
        if (this != OPEN) {
            throw new IllegalStateException();
        }
    }
}
