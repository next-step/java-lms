package nextstep.session.domain;

import java.util.Arrays;
import nextstep.session.SessionStatusInvalidException;

public enum SessionProgressStatus {
    PREPARING("준비중"),
    IN_PROGRESS("진행중"),
    FINISHED("종료"),
    ;
    private final String description;

    SessionProgressStatus(String description) {
        this.description = description;
    }

    public static SessionProgressStatus convert(String status) {
        return Arrays.stream(values())
            .filter(sessionType -> sessionType.description.equals(status))
            .findFirst()
            .orElseThrow(() -> new SessionStatusInvalidException("존재하지 않는 상태입니다."));
    }

    public String getDescription() {
        return description;
    }
}
