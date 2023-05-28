package nextstep.courses.domain.enums;

public enum SessionState {
    PREPARING(1),
    PROCEEDING(2),
    END(9);

    private int state;

    SessionState(int state) {
        this.state = state;
    }
}
