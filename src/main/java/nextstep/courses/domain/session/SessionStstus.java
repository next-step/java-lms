package nextstep.courses.domain.session;

public enum SessionStstus {
    OPEN,
    CLOSED;

    public void validateEnroll() {
        if (this != OPEN) {
            throw new IllegalStateException();
        }
    }
}
