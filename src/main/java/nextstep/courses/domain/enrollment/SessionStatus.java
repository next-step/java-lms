package nextstep.courses.domain.enrollment;

import nextstep.courses.exception.SessionStatusInvalidException;

import java.util.Arrays;

public enum SessionStatus {

    PREPARING("진행중", false),
    RECRUITING("모집중", true),
    FINISHED("종료", false)
    ;

    private final String status;

    private final boolean canEnroll;

    SessionStatus(String status, boolean canEnroll) {
        this.status = status;
        this.canEnroll = canEnroll;
    }

    public boolean cannotEnroll() {
        return !canEnroll;
    }

    public static SessionStatus convert(String status) {
        return Arrays.stream(values())
                .filter(sessionType -> sessionType.status.equals(status))
                .findAny()
                .orElseThrow(() -> new SessionStatusInvalidException(status));
    }

    public String get() {
        return status;
    }

}
