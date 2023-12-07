package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING("preparing"),
    APPLYING("applying"),
    CLOSED("closed");

    private final String sessionStatus;

    SessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }
}
