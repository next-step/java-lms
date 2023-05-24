package nextstep.courses.domain;

public enum SessionStatus {
    READY, OPEN, CLOSE;

    public boolean isReady() {
        return this.equals(SessionStatus.READY);
    }
}
