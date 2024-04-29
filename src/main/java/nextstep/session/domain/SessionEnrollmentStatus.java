package nextstep.session.domain;

import java.util.Arrays;
import nextstep.session.SessionStatusInvalidException;

public enum SessionEnrollmentStatus {
    OPEN("모집중"),
    CLOSED("비모집중");
    private final String description;

    SessionEnrollmentStatus(String description) {
        this.description = description;
    }

    public boolean isSessionOpenForRegistration() {
        return this == OPEN;
    }

    public static SessionEnrollmentStatus convert(String status) {
        return Arrays.stream(values())
            .filter(sessionType -> sessionType.description.equals(status))
            .findAny()
            .orElseThrow(() -> new SessionStatusInvalidException("존재하지 않는 상태입니다."));
    }

    public String getDescription() {
        return description;
    }
}
