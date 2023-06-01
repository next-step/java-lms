package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum SessionState {
    PREPARING(1),
    PROCEEDING(2),
    END(9);

    private int state;

    SessionState(int state) {
        this.state = state;
    }

    public static SessionState of(int state) {
        return (SessionState) Arrays.stream(SessionState.values())
                .filter(sessionState -> sessionState.getInt() == state)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid SessionState value: " + state));
    }

    public int getInt() {
        return state;
    }
}
