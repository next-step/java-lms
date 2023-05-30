package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionState {
    PREPARING(1),
    RECRUITING(2),
    CLOSED(3);

    private int index;

    SessionState(int index) {
        this.index = index;
    }

    public boolean canRegistering() {
        return this == RECRUITING;
    }

    public SessionState updateState() {
        if (isClosed(index)) {
            throw new IllegalArgumentException("이미 종료된 강의입니다.");
        }
        int next = index + 1;
        return Arrays.stream(values())
                .filter(state -> state.index == next)
                .findFirst()
                .orElseThrow(IllegalArgumentException::new);
    }

    public boolean isClosed(int index) {
        return CLOSED.index == index;
    }
}
