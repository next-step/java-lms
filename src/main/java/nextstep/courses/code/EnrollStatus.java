package nextstep.courses.code;

public enum EnrollStatus {

    READY,
    OPEN,
    CLOSED;

    public boolean isOpen() {
        return this.equals(OPEN);
    }
}
