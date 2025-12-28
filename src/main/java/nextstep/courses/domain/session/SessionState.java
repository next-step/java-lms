package nextstep.courses.domain.session;

public enum SessionState {
    READY,
    OPEN,
    CLOSED;

    public void validateEnroll() {
        if (this != OPEN) {
            throw new IllegalStateException();
        }
    }
}
