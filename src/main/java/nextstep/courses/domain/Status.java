package nextstep.courses.domain;

public enum Status {
    NOT_OPEN,
    OPEN,
    CLOSED;


    public Status ofOpen() {
        return OPEN;
    }

    public boolean isOpen() {
        return this == OPEN;
    }
}
