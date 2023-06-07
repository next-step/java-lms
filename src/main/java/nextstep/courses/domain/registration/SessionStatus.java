package nextstep.courses.domain.registration;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    PROGRESSING("진행중"),
    CLOSED("종료");

    private final String sessionStatus;

    SessionStatus(String sessionStatus) {
        this.sessionStatus = sessionStatus;
    }

    public static SessionStatus findByName(String sessionStatus) {
        return Arrays.stream(values())
                .filter(status -> status.isMatch(sessionStatus))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 강의상태 입니다."));
    }

    private boolean isMatch(String sessionStatus) {
        return this.sessionStatus.equals(sessionStatus);
    }

    public boolean isRecruiting() {
        return this == RECRUITING;
    }

    public boolean isNotProgressing() {
        return this != PROGRESSING;
    }
}
