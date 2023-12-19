package nextstep.sessions.domain;

public enum SessionProgressStatus {
    PREPARING("준비중"),
    PROGRESSING("진행중"),
    END("종료");

    private final String status;

    SessionProgressStatus(String status) {
        this.status = status;
    }
}
