package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionProgressStatus {
    READY, OPENED, CLOSED;

    public static SessionProgressStatus find(String sessionStatus) {
        return Arrays.stream(values())
                .filter(type -> type.name().equalsIgnoreCase(sessionStatus))
                .findAny()
                .orElseThrow(() -> new IllegalArgumentException("유효하지 않은 강의 상태입니다."));
    }

    public boolean canJoin() {
        return this.equals(OPENED) || this.equals(READY);
    }
}
