package nextstep.courses.domain.field;

import java.util.stream.Stream;

public enum SessionStatus {
    NOT_READY("notReady"),
    OPEN("open"),
    CLOSED("closed");

    private String status;

    SessionStatus(String status) {
        this.status = status;
    }

    public static SessionStatus of (String status) {
        return Stream.of(valueOf(status))
                .filter(sessionStatus ->
                        sessionStatus.status.equals(status))
                .findFirst()
                .orElseThrow(() -> new IllegalArgumentException("없는 상태값입니다."));
    }
}
