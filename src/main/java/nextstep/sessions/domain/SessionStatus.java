package nextstep.sessions.domain;

public enum SessionStatus {

    PREPARING("준비중"),
    RECRUITING("모집중"),
    TERMINATION("종료");

    private final String description;

    SessionStatus(String description) {
        this.description = description;
    }
}
