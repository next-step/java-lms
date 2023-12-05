package nextstep.sessions.domain;

import java.util.Arrays;

public enum Status {
    PREPARE("prepare"),
    RECRUITING("recruiting"),
    CLOSED("closed"),
    ;
    private final String status;

    Status(String status) {
        this.status = status;
    }
    public static Status of(String sessionStatus) {
        return Arrays.stream(values())
                .filter(status -> status.status.equals(sessionStatus))
                .findAny()
                .orElseThrow(()-> new IllegalArgumentException("허용되지 않은 상태 타입입니다."));
    }
}
