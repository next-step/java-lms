package nextstep.courses.domain;

public enum SessionStatus {
    READY,
    OPEN,
    CLOSED,
    ;

    public boolean isReady() {
        return READY == this;
    }

    public boolean isOpen() {
        return OPEN == this;
    }

    public boolean isClosed() {
        return CLOSED == this;
    }
}
