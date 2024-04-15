package nextstep.courses.code;

public enum EnrollStatus {

    READY,
    OPEN,
    CLOSED;

    EnrollStatus() {
    }

    public boolean isOpen() {
        return this.equals(OPEN);
    }
}
