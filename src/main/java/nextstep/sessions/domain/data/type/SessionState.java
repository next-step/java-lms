package nextstep.sessions.domain.data.type;

public enum SessionState {

    PREPARING("PREPARING", "준비중"),
    RECRUITING("RECRUITING", "모집중"),
    DONE("DONE", "종료");
    private final String state;
    private final String title;

    SessionState(String state, String title) {
        this.state = state;
        this.title = title;
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
