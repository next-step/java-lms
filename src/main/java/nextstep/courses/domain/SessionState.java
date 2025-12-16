package nextstep.courses.domain;

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
