package nextstep.sessions.domain;

public enum SessionState {
    PREPARING("준비중"),
    ONGOING("진행중"),
    FINISHED("종료");

    private String text;

    SessionState(String text) {
        this.text = text;
    }

    public boolean isFinished() {
        return this == FINISHED;
    }
}
