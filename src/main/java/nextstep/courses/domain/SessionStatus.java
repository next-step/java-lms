package nextstep.courses.domain;

import java.util.Map;

public enum SessionStatus {
    READY("준비중"),
    OPEN("모집중"),
    PROGRESS("진행중"),
    CLOSED("종료");

    private final String status;
    private static final Map<String, SessionStatus> sessionStatusMap = Map.of(READY.name(), READY, OPEN.name(), OPEN, PROGRESS.name(), PROGRESS, CLOSED.name(), CLOSED);

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean canEnrollment() {
        return this == OPEN || this == PROGRESS;
    }

    public static SessionStatus find(String status) {
        return sessionStatusMap.get(status);
    }
}
