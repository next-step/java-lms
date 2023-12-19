package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionState {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    END("종료");

    private String state;

    SessionState(String state) {
        this.state = state;
    }

    public String getState() {
        return this.state;
    }

    public static SessionState of(String state) {
        return Arrays.stream(values())
                .filter(SessionState -> SessionState.getState().equals(state))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 세션 상태입니다."));
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }

}
