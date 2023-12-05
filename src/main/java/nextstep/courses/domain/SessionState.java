package nextstep.courses.domain;

public enum SessionState {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    END("종료");

    private String status;

    SessionState (String status) {
        this.status = status;
    }
}
