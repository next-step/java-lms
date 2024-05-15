package nextstep.session.domain;

import java.util.Arrays;
import nextstep.session.SessionStatusInvalidException;

public enum EnrollmentStatus {
    OPEN("모집중"),
    CLOSED("비모집중");
    private final String description;

    EnrollmentStatus(String description) {
        this.description = description;
    }

    public boolean isSessionOpenForRegistration() {
        return this == OPEN;
    }

    public static EnrollmentStatus convert(String status) {
        return Arrays.stream(values())
            .filter(sessionType -> sessionType.description.equals(status))
            .findFirst()
            .orElseThrow(() -> new SessionStatusInvalidException("존재하지 않는 상태입니다."));
    }

    public String getDescription() {
        return description;
    }
}
