package nextstep.sessions.domain;

public enum SessionStatus {
    PREPARING,
    RECRUITING,
    END,
    IN_PROGRESS;

    public boolean isNotRecruiting() {
        return this != RECRUITING;
    }

    public boolean isNotInProgress() {
        return this != IN_PROGRESS;
    }
}
