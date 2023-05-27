package nextstep.courses.domain;

public enum SessionStatus {
    READY, OPENED, CLOSED;

    public boolean canJoin() {
        return this.equals(OPENED);
    }
}
