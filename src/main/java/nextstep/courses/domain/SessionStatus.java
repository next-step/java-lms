package nextstep.courses.domain;

public enum SessionStatus {
    PREPARING("준비중", false),
    RECRUITING("모집중", true),
    CLOSE("종료", false);

    private final String sessionStatus;
    private final boolean sessionResult;

    SessionStatus(String sessionStatus, boolean sessionResult) {
        this.sessionStatus = sessionStatus;
        this.sessionResult = sessionResult;
    }

    public String getSessionStatus() {
        return sessionStatus;
    }

    public boolean isSessionResult() {
        return sessionResult;
    }
}
