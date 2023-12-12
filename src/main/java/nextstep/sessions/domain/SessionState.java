package nextstep.sessions.domain;

import java.util.Arrays;

public enum SessionState {
    PREPARE("prepare"),
    RECRUIT("recruit"),
    END("end");

    private String state;

    SessionState(String state) {
        this.state = state;
    }

    public String getState() {
        return state;
    }

    public static SessionState matchState(String state) {
        return Arrays.stream(values())
                .filter(imageType -> imageType.getState().equals(state))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("강의 상태가 올바르지 않습니다."));
    }
}
