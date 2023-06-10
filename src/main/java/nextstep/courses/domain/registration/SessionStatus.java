package nextstep.courses.domain.registration;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING("준비중"),
    PROGRESSING("진행중"),
    CLOSED("종료"),
    NONE("없음");

    private final String sessionStatus;

    SessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public static SessionStatus findByName(String sessionStatus) {
        return Arrays.stream(values())
                .filter(status -> status.isMatch(sessionStatus))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isMatch(String sessionStatus) {
        return this.sessionStatus.equals(sessionStatus);
    }

    public boolean isNotProgressing() {
        return this != PROGRESSING;
    }
}
