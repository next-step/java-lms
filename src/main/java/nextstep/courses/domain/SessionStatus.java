package nextstep.courses.domain;

public enum SessionStatus {
    READY, OPENED, CLOSED;

    public boolean isOpened() {
        return this == OPENED;
    }
}
