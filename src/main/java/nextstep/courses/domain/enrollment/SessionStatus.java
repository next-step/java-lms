package nextstep.courses.domain.enrollment;

import nextstep.courses.domain.SessionType;
import nextstep.courses.exception.SessionStatusCannotEnrollmentException;
import nextstep.courses.exception.SessionStatusInvalidException;
import nextstep.courses.exception.SessionTypeInvalidException;

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

    public void canEnroll() {
        if (!this.canEnroll) {
            throw new SessionStatusCannotEnrollmentException(this);
        }
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
