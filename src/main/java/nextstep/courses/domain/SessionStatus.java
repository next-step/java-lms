package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionStatus {

    READY, RECRUITING, END;

    SessionStatus() {
    }

    public static SessionStatus of(String status) {
        return Arrays.stream(SessionStatus.values())
            .filter(sessionStatus -> sessionStatus.name().equalsIgnoreCase(status))
            .findFirst()
            .orElseThrow(() -> new IllegalArgumentException("해당하는 강의 상태가 없습니다."));
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }
}
