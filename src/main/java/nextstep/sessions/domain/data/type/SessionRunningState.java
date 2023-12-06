package nextstep.sessions.domain.data.type;

public enum SessionRunningState {

    PREPARING("PREPARING", "준비중"),
    RUNNING("RUNNING", "진행중"),
    DONE("DONE", "종료");
    private final String state;
    private final String title;

    SessionRunningState(String state, String title) {
        this.state = state;
        this.title = title;
    }

    public boolean isRunning() {
        return this == RUNNING;
    }
}
