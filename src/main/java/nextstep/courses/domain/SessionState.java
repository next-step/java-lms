package nextstep.courses.domain;

public enum SessionState {
    PREPARING,
    RECRUITING,
    CLOSED;

    public boolean canRegistering() {
        return this == RECRUITING;
    }

}
