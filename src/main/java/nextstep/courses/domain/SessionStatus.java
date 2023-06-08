package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING,
    RECRUITING,
    CLOSED;

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
