package nextstep.courses.domain.session;

public enum SessionStatus {
    PREPARING,
    RECRUITING,
    CLOSED;

    public boolean isEnrollable() {
        return this == RECRUITING;
    }
}
