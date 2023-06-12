package nextstep.sessions.domain;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING, RECRUITING, CLOSE;

    public boolean isRecruitable() {
        return this.equals(RECRUITING);
    }

    public static SessionStatus of(String status) {
        return Arrays.stream(values()).filter(s -> status.equals(s.name()))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("지원하지 않는 강의 상태입니다."));
    }
}
