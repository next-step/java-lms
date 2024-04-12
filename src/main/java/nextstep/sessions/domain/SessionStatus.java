package nextstep.sessions.domain;

public enum SessionStatus {
    PREPARING,
    RECRUITING,
    CLOSED
    ;

    public boolean canRecruit() {
        return this == RECRUITING;
    }
}
