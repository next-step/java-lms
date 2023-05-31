package nextstep.sessions.domain;

public enum SessionStatus {
    PREPARING, RECRUITING, CLOSE;

    public boolean isRecruitable() {
        return this.equals(RECRUITING);
    }
}
