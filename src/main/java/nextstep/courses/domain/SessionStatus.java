package nextstep.courses.domain;

public enum SessionStatus {
    READY,
    OPEN,
    CLOSED;

    public boolean isOpen() {
        return this == OPEN;
    }
}
