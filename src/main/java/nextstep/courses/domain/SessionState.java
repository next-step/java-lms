package nextstep.courses.domain;

public enum SessionState {
    PREPARE, OPEN, CLOSE;

    public boolean isOpen() {
        return this == OPEN;
    }
}
