package nextstep.courses.domain.enums;

import java.util.Arrays;

public enum ProgressState {
    PREPARING(1),
    PROCEEDING(2),
    END(9);

    private int state;

    ProgressState(int state) {
        this.state = state;
    }

    public static ProgressState of(int state) {
        return (ProgressState) Arrays.stream(ProgressState.values())
                .filter(sessionState -> sessionState.getInt() == state)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("Invalid ProgressState value: " + state));
    }

    public int getInt() {
        return state;
    }
}
