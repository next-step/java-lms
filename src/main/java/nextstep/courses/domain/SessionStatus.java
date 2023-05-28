package nextstep.courses.domain;

public enum SessionStatus {
    OPENED, RECRUITING, CLOSED;

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
