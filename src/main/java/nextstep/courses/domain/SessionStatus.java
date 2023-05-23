package nextstep.courses.domain;

import java.util.Arrays;

public enum SessionStatus {
    READY("준비중"),
    OPEN("모집중"),
    CLOSED("종료");

    private final String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public boolean canEnrollment() {
        return this == OPEN;
    }

    public String getStatus() {
        return status;
    }

    public static SessionStatus find(String status) {
        return Arrays.stream(values())
                .filter(x -> x.status == status)
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException(status + "는 없는 상태입니다."));
    }
}
