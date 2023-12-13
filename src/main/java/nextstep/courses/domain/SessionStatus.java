package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionStatus {
    PREPARING("준비중"),
    RECRUITING("모집중"),
    FINISHED("종료"),
    NONE("-");
    private String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public static SessionStatus of(String matchSessionStatus){
        return Arrays.stream(SessionStatus.values())
                .filter(sessionStatus -> sessionStatus.isSameStatus(matchSessionStatus))
                .findFirst()
                .orElse(NONE);
    }

    private boolean isSameStatus(String matchSessionStatus) {
        return this.status == matchSessionStatus;
    }
}
