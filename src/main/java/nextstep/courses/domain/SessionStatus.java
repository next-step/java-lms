package nextstep.courses.domain;

import java.util.Map;

public enum SessionStatus {
    READY("준비중"),
    OPEN("진행중"),
    CLOSED("종료");

    private final String status;
    private static final Map<String, SessionStatus> sessionStatusMap = Map.of(READY.getStatus(), READY, OPEN.getStatus(), OPEN, CLOSED.getStatus(), CLOSED);

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean canEnrollment() {
        return this == OPEN;
    }

    public String getStatus() {
        return status;
    }

    public static SessionStatus find(String status) {
        return sessionStatusMap.get(status);
    }
}
