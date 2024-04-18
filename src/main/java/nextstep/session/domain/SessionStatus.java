package nextstep.session.domain;

import java.util.Arrays;
import nextstep.session.SessionStatusInvalidException;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    FINISHED("종료"),
    ;
    private final String description;

    SessionStatus(String description) {
        this.description = description;
    }

    public boolean isSessionRecruiting() {
        return this == RECRUITING;
    }

    public static SessionStatus convert(String status) {
        return Arrays.stream(values())
            .filter(sessionType -> sessionType.description.equals(status))
            .findAny()
            .orElseThrow(() -> new SessionStatusInvalidException("존재하지 않는 상태입니다."));
    }

    public String getDescription() {
        return description;
    }
}
