package nextstep.sessions.domain;

public enum SessionState {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    FINISHED("종료");

    private String text;

    SessionState(String text) {
        this.text = text;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
