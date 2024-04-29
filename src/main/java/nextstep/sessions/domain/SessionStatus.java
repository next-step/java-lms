package nextstep.sessions.domain;

public enum SessionStatus {
    PREPARING,
    END,
    IN_PROGRESS;

    public boolean isNotInProgress() {
        return this != IN_PROGRESS;
    }
}
