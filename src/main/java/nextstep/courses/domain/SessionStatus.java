package nextstep.courses.domain;

import java.util.Arrays;
import java.util.HashMap;
import java.util.Map;

public enum SessionStatus {
    READY("준비중"),
    OPEN("모집중"),
    CLOSED("종료");

    private final String status;
    private static final Map<String, SessionStatus> sessionStatusMap = new HashMap<>();

    static {
        for (SessionStatus sessionStatus : SessionStatus.values()) {
            sessionStatusMap.put(sessionStatus.getStatus(), sessionStatus);
        }
    }

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
