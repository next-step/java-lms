package nextstep.courses.domain;

public enum SessionState {
    READY("준비중"),
    RECRUITING("모집중"),
    END("종료");

    SessionState(String state) {
        this.state = state;
    }

    private final String state;

    public boolean isAllowed() {
        return this == RECRUITING;
    }

    public String getState() {
        return state;
    }
}
