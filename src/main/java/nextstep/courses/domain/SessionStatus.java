package nextstep.courses.domain;

public enum SessionStatus {
    OPENED, RECRUITING, CLOSED;

    public static SessionStatus of(String status) {
        return SessionStatus.valueOf(status.toUpperCase());
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }

    public boolean isOpened() {
        return this == OPENED;
    }

    public boolean isClosed() {
        return this == CLOSED;
    }
}
